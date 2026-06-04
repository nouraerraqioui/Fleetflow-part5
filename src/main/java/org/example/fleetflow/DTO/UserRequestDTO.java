package org.example.fleetflow.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.model.Role;


@Setter
    @Getter
    public class UserRequestDTO {
        @NotBlank(message = "username est obligatoire")
        private String username;
        @Email(message = "email invalid")
        @NotBlank(message = "email est obligatoire")
        private String email;
        @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
        private String password;
        private Role role;

    }




