package com.et.service;

import com.et.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8088")
public interface UserServiceClient {

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);
}