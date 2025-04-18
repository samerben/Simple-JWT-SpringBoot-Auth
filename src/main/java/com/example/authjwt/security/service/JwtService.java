package com.example.authjwt.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//jwt/JwtService.java
@Component
public class JwtService {

 private final String SECRET = "somerandomsecretsomerandomsecretsomerandomsecretsomerandomsecret";

 public String generateToken(UserDetails user) {
     return Jwts.builder()
             .setSubject(user.getUsername())
             .setIssuedAt(new Date())
             .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 ساعة
             .signWith(SignatureAlgorithm.HS256, SECRET)
             .compact();
 }

 public String extractUsername(String token) {
     return Jwts.parser().setSigningKey(SECRET)
             .parseClaimsJws(token)
             .getBody().getSubject();
 }

 public boolean validateToken(String token, UserDetails user) {
     String username = extractUsername(token);
     return username.equals(user.getUsername()) && !isExpired(token);
 }

 private boolean isExpired(String token) {
     return Jwts.parser().setSigningKey(SECRET)
             .parseClaimsJws(token)
             .getBody().getExpiration().before(new Date());
 }
}
