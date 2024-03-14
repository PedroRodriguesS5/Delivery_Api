package com.deliveryproject.user;



import jakarta.persistence.*;

@Table(name = "users")
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String secondName;

    @Column
    private String password;

    @Column
    private String phoneNumber;




//    private []Addres addres;
}
