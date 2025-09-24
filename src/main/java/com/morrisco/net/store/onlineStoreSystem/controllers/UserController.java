package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.RegisterUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UpdateUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.onlineStoreSystem.mappers.UserMapper;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping()
    public Iterable<UserDto> getAllUsers (@RequestParam(required = false,defaultValue = "" ,name = "sort") String sortBy){

        if (!Set.of("name","email").contains(sortBy))
            sortBy= "name";

        return userRepository.findAll(Sort.by(sortBy)).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        var user = userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
          }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder){
       var user= userMapper.toEntity(request);

       userRepository.save(user);

        var userDto=userMapper.toDto(user);

        var uri =uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest request,@PathVariable(name = "id") int id){
        var user =userRepository.findById(id).orElse(null);

        if (user == null)
            return ResponseEntity.notFound().build();
        userMapper.updateUser(request,user);

        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
