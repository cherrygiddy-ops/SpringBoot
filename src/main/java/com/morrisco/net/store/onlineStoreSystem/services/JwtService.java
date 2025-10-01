package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.config.JwtConfig;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@AllArgsConstructor
public class JwtService {
    private final JwtConfig config;

    public String generateAccessToken(User user){
        //seconds in a day
        return getToken(user, config.getAccessTokenExpiration());

    }
    public String generateRefreshToken(User user){
        return getToken(user, config.getRefreshTokenExpiration());

    }

    private String getToken(User user, long expirationTime) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime))
                .signWith(config.getSecret())
                .compact();
    }

    public boolean  validateToken(String token){
        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }catch (Exception e){
            return false;
        }
    }

    //PayLoad
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(config.getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Integer getUserIdFromToken(String token){
       var claim = getClaims(token);
       return Integer.valueOf(claim.getSubject());
    }

}
