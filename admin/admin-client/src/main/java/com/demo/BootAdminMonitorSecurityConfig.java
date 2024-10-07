package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Yang douya
 * @date 2023/6/19 15:40
 */
@Configuration
public class BootAdminMonitorSecurityConfig {


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
                .csrf().disable().build();
    }
}
