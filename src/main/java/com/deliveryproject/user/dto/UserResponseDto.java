package com.deliveryproject.user.dto;

import com.deliveryproject.address.Address;
import com.deliveryproject.user.User;

import java.util.Set;
import java.util.UUID;

public record UserResponseDto(UUID id, String email, String name, String phoneNumber,String document, Set<Address> addresses) {
    public UserResponseDto(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(),
                user.getDocument(), user.getAddresses());
    };
}
