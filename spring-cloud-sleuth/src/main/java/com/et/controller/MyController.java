package com.et.controller;

import io.micrometer.tracing.SpanName;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class MyController {
    @Autowired
    com.et.service.DemoService demoService;
    @GetMapping("/hello")
    @NewSpan
    @SpanName("customSpanName")
    public String hello() {
        log.info("into controller ");
        return demoService.sayHello("jack");
    }
}