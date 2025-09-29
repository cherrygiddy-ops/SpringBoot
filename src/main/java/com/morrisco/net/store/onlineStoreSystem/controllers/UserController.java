package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.RegisterUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.ChangePasswordRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UpdateUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.exceptions.EmailExistsException;
import com.morrisco.net.store.onlineStoreSystem.mappers.UserMapper;
import com.morrisco.net.store.onlineStoreSystem.repositories.UserRepository;
import com.morrisco.net.store.onlineStoreSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;


    @GetMapping()
    public Iterable<UserDto> getAllUsers (@RequestParam(required = false,defaultValue = "" ,name = "sort") String sortBy){

//        if (!Set.of("name","email").contains(sortBy))
//            sortBy= "name";
//
//        return userRepository.findAll(Sort.by(sortBy)).stream()
//                .map(userMapper::toDto)
//                .toList();
        return null;
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
           @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder){
           var userDto =userService.registerUser(request);
        var uri =uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);


    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UpdateUserRequest request, @PathVariable(name = "id") int id){
        var user =userRepository.findById(id).orElse(null);

        if (user == null)
            return ResponseEntity.notFound().build();
        userMapper.updateUser(request,user);

        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") int id){
        var user =userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void>updatePassword(@PathVariable(name = "id") int id, @RequestBody ChangePasswordRequest request){
        var user =userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        if (!user.getPassword().equals(request.getOldPassword())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Map<String,String>>handleEmailExist(){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","Email is Already Registered"));
    }
}
