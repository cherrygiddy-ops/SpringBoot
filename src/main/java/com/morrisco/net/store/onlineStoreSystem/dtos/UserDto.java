package com.morrisco.net.store.onlineStoreSystem.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private  Integer id;
    private String name;
    private String email;

}
