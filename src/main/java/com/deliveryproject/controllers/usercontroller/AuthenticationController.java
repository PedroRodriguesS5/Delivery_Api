package com.deliveryproject.controllers.usercontroller;

import com.deliveryproject.dto.AddressRequestDto;
import com.deliveryproject.infra.security.TokenService;
import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.model.User;
import com.deliveryproject.dto.userDto.AuthenticationDTO;
import com.deliveryproject.dto.userDto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        List<UserResponseDto> getAllUsers = this.userRepository.findAll().stream().map(UserResponseDto::new).toList();
        return  ResponseEntity.ok(getAllUsers);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") String userId){
        Optional<User> userById = this.userRepository.findById(userId);
        if(userById.isEmpty()){
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        return ResponseEntity.ok(userById.stream().map(UserResponseDto::new));
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var userNamePassword =new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AddressRequestDto.LoginResponseDTO(token));
    }

}
