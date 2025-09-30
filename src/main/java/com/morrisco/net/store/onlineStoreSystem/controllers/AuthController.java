package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.JwtResponse;
import com.morrisco.net.store.onlineStoreSystem.dtos.LoginRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.mappers.UserMapper;
import com.morrisco.net.store.onlineStoreSystem.repositories.UserRepository;
import com.morrisco.net.store.onlineStoreSystem.services.JwtService;
import com.morrisco.net.store.onlineStoreSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
   private final UserMapper userMapper;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

      var user= userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
       var token = jwtService.generateTokens(user);
       
       return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/validate")
    public boolean  validateToken(@RequestHeader("Authorization") String authHeader){

      var token = authHeader.replace("Bearer ","");

        return jwtService.validateToken(token);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> currentUser (){
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var userId =(int)authentication.getPrincipal();
    var user = userRepository.findById(userId).orElse(null);
     if (user==null)
         return ResponseEntity.notFound().build();
     var userDto = userMapper.toDto(user);

     return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleUserFound(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}