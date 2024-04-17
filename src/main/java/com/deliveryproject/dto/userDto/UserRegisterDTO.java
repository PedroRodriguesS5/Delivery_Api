package com.deliveryproject.dto.userDto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UserRegisterDTO(
                                @Email(message = "Por favor insira um e-mail válido")
                                @NotBlank(message = "E-mail é obrigatório")
                                @NotNull(message = "E-mail é obrigatório")
                                String email,

                                @NotNull(message = "A senha é obrigatória")
                                @NotBlank(message = "A senha é obrigatória")
                                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$"
                                    ,message = "A senha deve conter pelo menos um caráctere especial," +
                                        " um número, uma letra maiúscula e 8 carácteres")
                                String password,
                                @NotBlank(message = "O nome é obrigatório")
                                @Size(min = 3, max = 30, message = "Insira um nome válido por favor.")
                                @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$" , message = "Por favor insira um nome válido" )
                                String name,
                                @NotBlank(message = "Telefone é um campo obrigatório!")
                                @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$",
                                        message = "Por favor insira um telefone válido")
                                String phoneNumber,

                                @CPF(message = "Por favor insira um cpf válido")
                                @NotBlank(message = "CPF é um campo obrigatório!")
                                String document) {
}
