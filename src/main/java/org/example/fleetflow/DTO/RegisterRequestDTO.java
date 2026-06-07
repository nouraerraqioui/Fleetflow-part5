package org.example.fleetflow.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.model.Role;


@Setter
@Getter
    public class RegisterRequestDTO {
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, max = 20,
            message = "Le mot de passe doit contenir entre 8 et 20 caractères")
    private String password;
    @Email(message = "email est obligatoire")
    private String email;

    private Role role;

    // Chauffeur specific fields
    private String nom;
    private String telephone;
    private String permisType;
}






