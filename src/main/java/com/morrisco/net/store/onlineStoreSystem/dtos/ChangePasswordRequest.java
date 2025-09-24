package com.morrisco.net.store.onlineStoreSystem.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private  String newPassword;
}
