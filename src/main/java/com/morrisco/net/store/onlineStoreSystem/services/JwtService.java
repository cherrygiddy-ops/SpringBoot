package com.morrisco.net.store.onlineStoreSystem.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    public String generateTokens(String email){
        final long expirationDate =86400; //seconds in a day
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000 *expirationDate) )
                .signWith(Keys.hmacShaKeyFor("hello".repeat(8).getBytes()))
                .compact();
    }
}
