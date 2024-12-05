package com.et;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service-b")
public class ServiceBController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call-a")
    public String callServiceA() {
        String url = "http://service-a/service-a/hello";
        return restTemplate.getForObject(url, String.class);
    }
}