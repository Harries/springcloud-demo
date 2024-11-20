package com.et;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableIntegration
public class LeaderElectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaderElectionApplication.class, args);
    }
}

