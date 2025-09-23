package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping()
    public Iterable<User> getAllUsers (){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userRepository.findById(id).orElse(null);
    }
}
