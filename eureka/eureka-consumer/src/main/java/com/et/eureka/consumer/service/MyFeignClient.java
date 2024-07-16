package com.et.eureka.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider")
public interface MyFeignClient {

    @GetMapping("/student/findAll")
    String findAll();
}
