package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.LoginRequest;
import com.morrisco.net.store.onlineStoreSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleUserFound(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}