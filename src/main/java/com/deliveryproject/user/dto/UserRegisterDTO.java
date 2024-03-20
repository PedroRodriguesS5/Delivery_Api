package com.deliveryproject.user.dto;

import com.deliveryproject.user.UserRole;

public record UserRegisterDTO(String email, String password, String name, String phoneNumber,
                              String document) {
}
