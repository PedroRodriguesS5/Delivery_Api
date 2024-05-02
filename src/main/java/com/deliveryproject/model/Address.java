package com.deliveryproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "cep", nullable = false)
    @Size(min = 8, max = 8, message = "Por favor insira um CEP válido")
    @Pattern(regexp = "^[0-9]*&")
    @NotBlank(message = "O cep é obrigatório")
    private String cep;

    @Column(name = "street", nullable = false)
    @NotBlank
    private String street;

    @Column(name = "neighborhood", nullable = false)
    @NotBlank
    private String neighborhood;

    @Column(name = "city", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank
    private String state;

    @Column(name = "numberHouse", nullable = false)
    @NotBlank
    @Pattern(regexp = "^[0-9]")
    @Size(max = 5)
    private Short numberHouse;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    private LocalDateTime created_at;
}
