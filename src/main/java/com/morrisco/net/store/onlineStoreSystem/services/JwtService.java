package com.morrisco.net.store.onlineStoreSystem.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateTokens(String email){
        final long expirationDate =86400; //seconds in a day
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000 *expirationDate) )
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public boolean  validateToken(String token){
        try {
            var claims =Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration().after(new Date());
        }catch (Exception e){
            return false;
        }
    }

}
