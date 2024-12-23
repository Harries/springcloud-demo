package com.et.interceptor;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuhaihua
 * @version 1.0
 * @ClassName JSONPlaceHolderInterceptor
 * @Description todo
 * @date 2024/12/23/ 14:18
 */
@Configuration
public class JSONPlaceHolderInterceptor  {
    @Value("${feign.client.config.default.username}")
    private String username;

    @Value("${feign.client.config.default.password}")
    private String password;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("user", username);
            requestTemplate.header("password", password);
            requestTemplate.header("Accept", "application/json");
        };
    }
}
