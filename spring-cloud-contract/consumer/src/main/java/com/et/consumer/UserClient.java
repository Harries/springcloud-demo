package com.et.consumer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getUser(Long id) {
        return restTemplate.getForObject("http://localhost:8080/user/" + id, UserDTO.class);
    }
}