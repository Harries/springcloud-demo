package com.et.service;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuhaihua
 * @version 1.0
 * @ClassName DemoService
 * @Description todo
 * @date 2024/12/03/ 17:37
 */
@Service
@Slf4j
public class DemoService {
    @NewSpan
    public String sayHello(String name){
        log.info("hello "+name);
        return "hello "+name;
    }
}
