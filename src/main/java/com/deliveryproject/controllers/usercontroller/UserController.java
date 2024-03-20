package com.deliveryproject.controllers.usercontroller;

import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.user.User;
import com.deliveryproject.user.UserRole;
import com.deliveryproject.user.dto.UserRegisterDTO;
import com.deliveryproject.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPass = new BCryptPasswordEncoder().encode(data.password());
        User newUser = User.builder().
                email(data.email()).
                name(data.name()).
                document(data.document()).password(encryptedPass).
                phoneNumber(data.phoneNumber()).role(UserRole.valueOf("User"))
                .build();

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}


