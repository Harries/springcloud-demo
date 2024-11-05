package com.et.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@RestController
@RequestMapping("/annotation")
public class MyController {

    private final io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;
    private boolean simulateFailure = true;

    public MyController(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("order-service");
    }

    @GetMapping("/my-service")
    @CircuitBreaker(name = "order-service", fallbackMethod = "fallbackMethod")
    public String myService() {
        System.out.println("Circuit Breaker State: " + circuitBreaker.getState());
        System.out.println("Circuit Breaker name: " + circuitBreaker.getName());
        System.out.println("Circuit Breaker config: " + circuitBreaker.getCircuitBreakerConfig().toString());
        if (simulateFailure) {
            throw new RuntimeException("Simulated failure");
        }
        return "Service is up";
    }

    // 回退方法
    public String fallbackMethod(Throwable throwable) {
        if (throwable instanceof CallNotPermittedException) {
            return "Circuit Breaker is OPEN, request not permitted";
        }
        return "Fallback response";
    }

    @GetMapping("/toggle-failure")
    public String toggleFailure() {
        simulateFailure = !simulateFailure;
        return "Failure simulation is now " + (simulateFailure ? "ON" : "OFF");
    }
} 