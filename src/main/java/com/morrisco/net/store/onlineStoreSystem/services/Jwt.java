package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.Role;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.crypto.SecretKey;
import java.util.Date;

@ToString
@AllArgsConstructor
public class Jwt {
    private Claims claims;
    private SecretKey secretKey;


    public boolean isExpired(){
        return claims.getExpiration().before(new Date());
    }

    public Integer getUserIdFromToken(String token){
        return Integer.valueOf(claims.getSubject());
    }
    public Role getRoleFromToken(String token){
        return Role.valueOf(claims.get("role",String.class));
    }

    public  String toString() {
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
