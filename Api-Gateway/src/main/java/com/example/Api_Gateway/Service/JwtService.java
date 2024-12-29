package com.example.Api_Gateway.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secret;

    private SecretKey getSecret(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Long getUserId(String token) {

        System.out.println("jwtservice token "+token);
        //if token is expired then exception will be thrown
        Claims claims= Jwts.parser().verifyWith(getSecret())
                .build()
                .parseSignedClaims(token.trim())
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}
