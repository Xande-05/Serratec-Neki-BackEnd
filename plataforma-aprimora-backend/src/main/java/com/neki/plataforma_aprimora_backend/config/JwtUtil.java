package com.neki.plataforma_aprimora_backend.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final Long EXPIRATION_TIME = 86400000L;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(Long adminId, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", adminId);
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getAdminIdFromToken(String token) {
        return extrairClaims(token).get("adminId", Long.class);
    }

    public String getEmailFromToken(String token) {
        return extrairClaims(token).get("email", String.class);
    }

    public boolean validarToken(String token) {
        try {
            extrairClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extrairClaims(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}