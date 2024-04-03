package com.deliveryproject.address.dto;

public record AddressRequestDto(String cep, String street, String neighborhood,
                                String city, String state, Short numberHouse) {
}
