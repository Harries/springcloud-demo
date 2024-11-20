package com.et;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LeaderElectionConfig {

    private static final String LEADER_KEY = "service/leader";
    private static final String CONSUL_URL = "http://localhost:8500";
    private String sessionId;


    @Bean
    @InboundChannelAdapter(value = "leaderChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<String> leaderMessageSource() {
        return () -> {
            // Implement logic to check if this instance is the leader
            boolean isLeader = checkLeadership();
            return MessageBuilder.withPayload(isLeader ? "I am the leader" : "I am not the leader").build();
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "leaderChannel")
    public MessageHandler leaderMessageHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getPayload());
                // Implement logic to perform leader-specific tasks
            }
        };
    }


    private final RestTemplate restTemplate = new RestTemplate();

    public LeaderElectionConfig() {
        this.sessionId = createSession();
    }

    private String createSession() {
        String url = CONSUL_URL + "/v1/session/create";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("{\"Name\": \"leader-election-session\"}", headers);
        //ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        //  PUT
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        // Extract session ID from response
        return response.getBody().split("\"")[3]; // This is a simple way to extract the session ID
    }

    public boolean checkLeadership() {
        String url = CONSUL_URL + "/v1/kv/" + LEADER_KEY + "?acquire=" + sessionId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }
    public void releaseLeadership() {
        String url = CONSUL_URL + "/v1/kv/" + LEADER_KEY + "?release=" + sessionId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);
        if (Boolean.TRUE.equals(response.getBody())) {
            System.out.println("Released leadership successfully");
        } else {
            System.out.println("Failed to release leadership");
        }
    }
    @PreDestroy
    public void onExit() {
        releaseLeadership();
    }
}