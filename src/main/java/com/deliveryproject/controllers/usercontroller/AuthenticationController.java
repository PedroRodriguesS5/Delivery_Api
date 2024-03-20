package com.deliveryproject.controllers.usercontroller;

import com.deliveryproject.infra.security.TokenService;
import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.user.User;
import com.deliveryproject.user.dto.AuthenticationDTO;
import com.deliveryproject.user.dto.LoginResponseDTO;
import com.deliveryproject.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/users")
    public ResponseEntity getAll(){
        List<UserResponseDto> getAllUsers = userRepository.findAll().stream().map(UserResponseDto::new).toList();
        return  ResponseEntity.ok(getAllUsers);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var userNamePassword =new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
