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
       // 默认保存1000条http请求记录
       repository.setCapacity(1000);
       return repository;
   }
}

