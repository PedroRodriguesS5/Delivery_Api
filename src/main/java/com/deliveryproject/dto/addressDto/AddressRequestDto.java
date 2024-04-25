package com.deliveryproject.dto.addressDto;

public record AddressRequestDto(String cep, String street, String neighborhood,
                                String city, String state, Short numberHouse) {

}
