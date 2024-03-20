package com.deliveryproject.address;

import com.deliveryproject.user.User;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "numberhouse")
    private Short numberHouse;


    @ManyToMany(mappedBy = "addresses")
    Set<User> users;
}
