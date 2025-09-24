package com.morrisco.net.store.onlineStoreSystem.mappers;

import com.morrisco.net.store.onlineStoreSystem.dtos.RegisterUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UpdateUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserDto toDto (User user);
    User toEntity(RegisterUserRequest registerUserRequest);
    void updateUser(UpdateUserRequest updateUserRequest,@MappingTarget User user);
}
