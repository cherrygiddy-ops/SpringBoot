package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.LoginRequest;
import com.morrisco.net.store.onlineStoreSystem.exceptions.EmailNotFoundException;
import com.morrisco.net.store.onlineStoreSystem.exceptions.IncorrectPasswordExecption;
import com.morrisco.net.store.onlineStoreSystem.exceptions.UserFoundException;
import com.morrisco.net.store.onlineStoreSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
   private final UserService userService;
    @PostMapping("/login")
    public void login(@Valid  @RequestBody LoginRequest loginRequest){
      userService.login(loginRequest);
    }
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?>handleUserFound(){
        return  ResponseEntity.ok().build();
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?>handleEmailNotFound(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Email Doesn't Exists"));
    }

    @ExceptionHandler(IncorrectPasswordExecption.class)
    public ResponseEntity<?>handleIncorretPasswordFound(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Incorrect Password"));
    }
}
