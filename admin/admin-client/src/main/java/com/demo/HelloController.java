package com.demo;


import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String findAll(){
        return "hello world";
    }

}
