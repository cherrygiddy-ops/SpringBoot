package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.config.JwtConfig;
import com.morrisco.net.store.onlineStoreSystem.entities.Role;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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


    public Jwt generateAccessToken(User user){
        return  getToken(user, config.getAccessTokenExpiration());
    }
    public Jwt generateRefreshToken(User user){
        return getToken(user, config.getRefreshTokenExpiration());

    }
    public  Jwt getToken(User user, long expirationTime) {
        var claims=Jwts.claims()
                .subject(user.getId().toString())
                .add("name", user.getName())
                .add("email", user.getEmail())
                .add("role",user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime)) .build()  ;
        return new Jwt(claims, config.getSecret());
    }
    public Jwt parseToken (String token){
        try {
            var claims = getClaims(token);
            return new Jwt(claims, config.getSecret());
        }catch (JwtException e){
            return null;

        }
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(config.getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }





    //PayLoad



}
