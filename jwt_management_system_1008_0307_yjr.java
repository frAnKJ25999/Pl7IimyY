// 代码生成时间: 2025-10-08 03:07:19
package com.example.jwtmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@SpringBootApplication
@RestController
public class JwtManagementSystem {

    private static final long EXPIRATION_TIME = 5 * 60 * 60;
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static void main(String[] args) {
        SpringApplication.run(JwtManagementSystem.class, args);
    }

    @GetMapping("/generate-token")
    public String generateToken(String username) {
        try {
            String jwt = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                    .signWith(KEY)
                    .compact();
            return jwt;
        } catch (Exception e) {
            throw new RuntimeException("Error generating token");
        }
    }

    @GetMapping("/verify-token")
    public boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
