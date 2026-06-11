package com.shipment.logistics.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET = "mysecretkeymysecretkey" + "mysecretkey123456";

	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

	// GENERATE TOKEN
	public String generateToken(String username) {

		return Jwts.builder()

				.setSubject(username)

				.setIssuedAt(new Date())

				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))

				.signWith(key).compact();
	}

	public String extractUsername(String token) {

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
}
