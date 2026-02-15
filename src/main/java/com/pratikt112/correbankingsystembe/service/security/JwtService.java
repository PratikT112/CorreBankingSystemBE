package com.pratikt112.correbankingsystembe.service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    private final String SECRET = "CBSSecretKeyForJsonWebTokenGeneration";
    private final long EXPIRATION = 2 * 60 * 60 * 1000; // 2 hours
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String tellerNo, String userType){
        String jti = UUID.randomUUID().toString();

        return Jwts.builder()
                .subject(tellerNo).id(jti)
                .claim("userType", userType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser().set
    }
}
