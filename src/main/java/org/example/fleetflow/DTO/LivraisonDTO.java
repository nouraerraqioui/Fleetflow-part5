package org.example.fleetflow.DTO;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LivraisonDTO {

    private Long id;
    private LocalDateTime dateLivraison;

    @NotBlank(message = "L'adresse de départ est obligatoire")
    private String adresseDepart;

    @NotBlank(message = "L'adresse de destination est obligatoire")
    private String adresseDestination;

    @NotNull(message = "Le statut est obligatoire")
    private Livraison.StatutLivraison statut;

    @NotNull(message = "Le chauffeur est obligatoire")
    private Long chauffeurId;

    @NotNull(message = "Le client est obligatoire")
    private Long clientId;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long vehiculeId;
}
