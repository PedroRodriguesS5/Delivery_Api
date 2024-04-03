package com.deliveryproject.address;

import com.deliveryproject.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "addresses")
@Table(name = "addresses")
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "cep")
    private String cep;
    @Column(name = "street")
    private String street;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "numberHouse")
    private Short numberHouse;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User users;
}
