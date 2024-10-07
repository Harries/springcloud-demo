package com.demo;


import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {

   @Bean
   public HttpExchangeRepository httpTraceRepository() {
       InMemoryHttpExchangeRepository repository = new InMemoryHttpExchangeRepository();
       // save 1000 http record
       repository.setCapacity(1000);
       return repository;
   }
}

