package com.deliveryproject.dto.userDto;

import com.deliveryproject.model.User;

public record UserResponseDto( String email, String name, String phoneNumber,String document) {
    public UserResponseDto(User user){
        this( user.getName(), user.getEmail(), user.getPhoneNumber(),
                user.getDocument());
    };
}
