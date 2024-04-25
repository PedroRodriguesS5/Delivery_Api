package com.deliveryproject.controllers.addresscontroller;

import com.deliveryproject.model.Address;
import com.deliveryproject.dto.addressDto.AddressRequestDto;
import com.deliveryproject.dto.addressDto.AddressResponseDto;
import com.deliveryproject.repositories.AddressRepository;
import com.deliveryproject.repositories.UserRepository;
import com.deliveryproject.model.User;
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
            List<Address> existsAddress = this.addressRepository.findAllByUsersId(userID).stream().toList();
        Address newAddress = Address.builder().
                cep(data.cep())
                .city(data.city())
                .state(data.state())
                .street(data.street())
                .neighborhood(data.neighborhood())
                .numberHouse(data.numberHouse())
                .users(userById)
                .build();
        if(existsAddress.stream().anyMatch(address -> address.getId().equals(newAddress.getId()))){
            return ResponseEntity.badRequest().body("Endereço já cadastrado");
        }
        this.addressRepository.save(newAddress);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
