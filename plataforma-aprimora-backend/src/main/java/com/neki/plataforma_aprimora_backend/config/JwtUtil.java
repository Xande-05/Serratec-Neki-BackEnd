package com.neki.plataforma_aprimora_backend.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private final Long EXPIRATION_TIME = (long) 86400000;
	
	public String gerarToken(Long adminId, String email) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("adminId", adminId);
		claims.put("email", email);
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();
	}
	
	public Long getAdminIdFromToken(String token) {
		Claims claims = extrairClaims(token);
		
		return claims.get("adminId", Long.class);
	}
	
	public String getEmailFromToken(String token) {
		Claims claims = extrairClaims(token);

		return claims.get("email", String.class);
	}
	
	public boolean validarToken (String token) {
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
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
