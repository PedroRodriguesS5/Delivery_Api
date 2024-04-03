package com.deliveryproject.address.dto;

import com.deliveryproject.address.Address;

public record AddressResponseDto( String cep, String City,
                                 String neighborhood, String street, String state, Short numberHouse) {
    public AddressResponseDto(Address address){
        this(address.getCep(),address.getCity(), address.getNeighborhood(), address.getStreet(),
                address.getState(), address.getNumberHouse());
    }
}
