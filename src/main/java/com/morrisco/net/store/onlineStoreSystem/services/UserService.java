package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.dtos.RegisterUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.exceptions.EmailExistsException;
import com.morrisco.net.store.onlineStoreSystem.mappers.UserMapper;
import com.morrisco.net.store.onlineStoreSystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserDto registerUser(RegisterUserRequest request){
        var user= userMapper.toEntity(request);
        if (userRepository.existsByEmail(request.getEmail()))
            throw  new EmailExistsException();
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
