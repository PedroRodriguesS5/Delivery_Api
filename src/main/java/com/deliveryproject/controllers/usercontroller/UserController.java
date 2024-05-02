package com.deliveryproject.controllers.usercontroller;

import com.deliveryproject.exceptions.UniqueConstraintException;
import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.model.User;
import com.deliveryproject.services.UserRole;
import com.deliveryproject.dto.userDto.UserRegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register( @RequestBody @Valid UserRegisterDTO data) throws Exception{

        try {
        String encryptedPass = passwordEncoder.encode(data.password());
        User newUser = User.builder().
                email(data.email()).
                name(data.name()).
                document(data.document()).
                 password(encryptedPass).
                phoneNumber(data.phoneNumber()).role(UserRole.valueOf("User"))
                .build();
        this.userRepository.save(newUser);
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso");
        }catch (DataIntegrityViolationException e) {
            if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException)
                    e.getMostSpecificCause()).getSQLState().equals("23505"))
                throw new UniqueConstraintException( e.getMostSpecificCause());
            throw e;
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}


