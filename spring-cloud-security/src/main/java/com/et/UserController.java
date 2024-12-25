package com.et;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // 注册逻辑
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 验证用户并生成JWT
        String token = jwtTokenProvider.createToken(loginRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}