package com.et;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SecretController {

    @Value("${example.username}")
    private String username;

    @Value("${example.password}")
    private String password;

    @GetMapping("/secrets")
    public String getSecrets() {
        return "Username: " + username + ", Password: " + password;
    }
}