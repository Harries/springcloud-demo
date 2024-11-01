package com.et.function;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomFunctions {

    @Bean
    public Function<String, String> toUpperCase() {
        return value -> value.toUpperCase();
    }

    @Bean
    public Function<String, String> toLowerCase() {
        return value -> value.toLowerCase();
    }
}