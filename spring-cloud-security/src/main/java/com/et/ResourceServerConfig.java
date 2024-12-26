package com.et;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/api/public").permitAll()
            .requestMatchers("/api/protected").authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt(); // 使用 JWT 作为资源服务器的认证方式

        return http.build();
    }
}