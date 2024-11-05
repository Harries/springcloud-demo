package com.et.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

import java.util.function.Supplier;

@RestController
public class DemoController {

    private final CircuitBreaker circuitBreaker;
    private boolean simulateFailure = true;

    public DemoController(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("myCircuitBreaker");
    }
    @GetMapping("/my-service")
    public String myService() {
        System.out.println("Circuit Breaker State: " + circuitBreaker.getState());

        Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            return "Service is up";
        });

        try {
            return decoratedSupplier.get();
        } catch (CallNotPermittedException e) {
            return "Circuit Breaker is OPEN, request not permitted";
        } catch (Exception e) {
            return "Fallback response";
        }
    }

    @GetMapping("/toggle-failure")
    public String toggleFailure() {
        simulateFailure = !simulateFailure;
        return "Failure simulation is now " + (simulateFailure ? "ON" : "OFF");
    }
}