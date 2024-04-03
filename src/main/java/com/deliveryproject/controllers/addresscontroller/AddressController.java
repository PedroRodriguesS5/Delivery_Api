package com.deliveryproject.controllers.addresscontroller;

import com.deliveryproject.address.Address;
import com.deliveryproject.address.dto.AddressRequestDto;
import com.deliveryproject.address.dto.AddressResponseDto;
import com.deliveryproject.repositories.AddressRepository;
import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.user.User;
import com.deliveryproject.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("address")
public class AddressController {


    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity getAllUserAddress(@PathVariable(value = "id") String userId){
        List<AddressResponseDto> userAddresses =
                this.addressRepository.findAllByUsersId(userId).stream().map(AddressResponseDto::new).toList();

        return ResponseEntity.ok(userAddresses);
    }

    @PostMapping("/register/{id}")
    public ResponseEntity registerAddress(@RequestBody AddressRequestDto data, @PathVariable(value = "id")String userID){
        User userById = this.userRepository.findById(userID).orElseThrow(()->new RuntimeException("Usuário não encontrado"));

        if(userById != null){
        Address newAddress = Address.builder().
                cep(data.cep())
                .city(data.city())
                .state(data.state())
                .street(data.street())
                .neighborhood(data.neighborhood())
                .numberHouse(data.numberHouse())
                .users(userById)
                .build();
        this.addressRepository.save(newAddress);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
