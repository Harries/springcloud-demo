package com.et;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTokenUtil {

    public static String generateJwt(String secretKey, String subject, String issuer) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 小时过期
                .signWith(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"), SignatureAlgorithm.HS256)
                .compact();
    }
}
