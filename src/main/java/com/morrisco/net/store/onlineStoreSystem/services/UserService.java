package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.dtos.LoginRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.RegisterUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.exceptions.EmailExistsException;
import com.morrisco.net.store.onlineStoreSystem.exceptions.EmailNotFoundException;
import com.morrisco.net.store.onlineStoreSystem.exceptions.IncorrectPasswordExecption;
import com.morrisco.net.store.onlineStoreSystem.exceptions.UserFoundException;
import com.morrisco.net.store.onlineStoreSystem.mappers.UserMapper;
import com.morrisco.net.store.onlineStoreSystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public UserDto registerUser(RegisterUserRequest request){
        var user= userMapper.toEntity(request);
        if (userRepository.existsByEmail(request.getEmail()))
            throw  new EmailExistsException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public List<UserDto> getUserDtos(String sortBy) {
        if (!Set.of("name","email").contains(sortBy))
            sortBy = "name";

        return userRepository.findAll(Sort.by(sortBy)).stream()
                .map(userMapper::toDto)
                .toList();
    }

    public void login(LoginRequest request){
        var user =userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user==null)
            throw new EmailNotFoundException();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IncorrectPasswordExecption();
        throw new UserFoundException();
    }


}
