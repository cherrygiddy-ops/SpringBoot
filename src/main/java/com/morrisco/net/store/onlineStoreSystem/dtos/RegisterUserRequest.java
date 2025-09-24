package com.morrisco.net.store.onlineStoreSystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;
}
