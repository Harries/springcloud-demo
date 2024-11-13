package com.et.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhaihua
 * @version 1.0
 * @ClassName DemoController:
 * @Description todo
 * @date 2024/11/01/ 13:47
 */
@RestController
@RefreshScope
public class DemoController {
    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping("/property")
    public String getProperty() {
        return exampleProperty;
    }
}
