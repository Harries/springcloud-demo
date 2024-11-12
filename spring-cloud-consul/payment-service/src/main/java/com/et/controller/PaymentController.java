package com.et.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/pay")
    public String processPayment() {
        return "Payment processed successfully!";
    }
}