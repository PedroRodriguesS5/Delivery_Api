package com.deliveryproject.controllers.usercontroller;

import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.model.User;
import com.deliveryproject.services.UserRole;
import com.deliveryproject.dto.userDto.UserRegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register( @RequestBody @Valid UserRegisterDTO data){
        Optional<User> user = this.userRepository.findByEmail(data.email());

        if(user.isPresent()) return ResponseEntity.badRequest().build();
        String encryptedPass = passwordEncoder.encode(data.password());
        User newUser = User.builder().
                email(data.email()).
                name(data.name()).
                document(data.document()).
                password(encryptedPass).
                phoneNumber(data.phoneNumber()).role(UserRole.valueOf("User"))
                .build();
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}


