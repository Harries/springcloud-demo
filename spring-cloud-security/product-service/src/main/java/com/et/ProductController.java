package com.et;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestHeader("Authorization") String token) {
        // 验证JWT
        if (!jwtTokenProvider.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        // 返回商品列表
        return ResponseEntity.ok(productService.getAllProducts());
    }
}