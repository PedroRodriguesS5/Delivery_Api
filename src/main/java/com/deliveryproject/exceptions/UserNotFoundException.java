package com.deliveryproject.exceptions;

import com.deliveryproject.dto.userDto.UserResponseDto;
import com.deliveryproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){super("Usuário não encontrado");}
    public UserNotFoundException(String message){super(message);}
}
