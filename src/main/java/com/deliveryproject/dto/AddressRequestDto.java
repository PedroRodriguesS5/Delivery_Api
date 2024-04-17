package com.deliveryproject.dto;

public record AddressRequestDto(String cep, String street, String neighborhood,
                                String city, String state, Short numberHouse) {
    public static record LoginResponseDTO(String token) {
    }
}
