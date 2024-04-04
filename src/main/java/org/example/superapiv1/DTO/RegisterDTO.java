package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "O login não pode estar em branco")
        @Size(min = 5, max = 50, message = "O login deve ter entre 5 e 50 caracteres")
        String login,

        @Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres")
        @Pattern(regexp = "\\d{8}", message = "O CEP deve conter apenas dígitos")
        String cep,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres")
        String password) {
}
