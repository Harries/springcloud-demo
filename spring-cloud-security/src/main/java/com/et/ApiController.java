package com.et;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/public")
    public ResponseEntity<String> publicResource() {
        return ResponseEntity.ok("This is a public resource");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedResource() {
        return ResponseEntity.ok("This is a protected resource");
    }
}