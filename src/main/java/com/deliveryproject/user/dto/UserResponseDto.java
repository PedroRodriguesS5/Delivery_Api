package com.deliveryproject.user.dto;

import com.deliveryproject.user.User;

public record UserResponseDto(String id, String email, String name, String phoneNumber,String document) {
    public UserResponseDto(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(),
                user.getDocument());
    };
}
