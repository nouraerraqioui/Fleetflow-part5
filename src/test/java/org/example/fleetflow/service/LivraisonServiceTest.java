package org.example.fleetflow.service;

import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.Repository.ClientRepository;
import org.example.fleetflow.Repository.LivraisonRepository;
import org.example.fleetflow.Repository.VehiculeRepository;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LivraisonServiceTest {

    @Mock
    LivraisonRepository livraisonRepository;
    @Mock
    LivraisonMapper livraisonMapper;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ChauffeurRepository chauffeurRepository;
    @Mock
    VehiculeRepository vehiculeRepository;
    @InjectMocks
    LivraisonService livraisonService;


    @Test
    void assignerChauffeurVehicule() {
        Livraison livraison = new Livraison();
        livraison.setId(1L);

        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setId(2L);
        chauffeur.setNom("ahmed");
        chauffeur.setDisponible(true);

        Vehicule vehicule = new Vehicule();
        vehicule.setId(3L);
        vehicule.setMatricule("AB28");
        vehicule.setStatut(Vehicule.StatutVehicule.DISPONIBLE);

        LivraisonDTO livraisonDTO = new LivraisonDTO();
        livraisonDTO.setId(1L);


        when(chauffeurRepository.findById(2L)).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(3L)).thenReturn(Optional.of(vehicule));
        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(livraisonRepository.save(livraison)).thenReturn(livraison);
        when(livraisonMapper.toDTO(livraison)).thenReturn(livraisonDTO);

        LivraisonDTO resultat = livraisonService.assignerChauffeurVehicule(1L,2L,3L);



    }

    @Test
    void modifierStatutLivraison() {


        Livraison livraison = new Livraison();
        livraison.setId(1L);
        livraison.setStatut(Livraison.StatutLivraison.ENATTENTE);

        Livraison mod = new Livraison();
        mod.setId(1L);
        mod.setStatut(Livraison.StatutLivraison.LIVREE);

        LivraisonDTO livraisonDTO = new LivraisonDTO();
        livraisonDTO.setStatut(Livraison.StatutLivraison.LIVREE);


        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));


        when(livraisonRepository.save(Mockito.any(Livraison.class))).thenReturn(mod);


        when(livraisonMapper.toDTO(mod)).thenReturn(livraisonDTO);

        LivraisonDTO dto = livraisonService.modifierStatutLivraison(1L, Livraison.StatutLivraison.LIVREE);

        assertEquals(Livraison.StatutLivraison.LIVREE, dto.getStatut());
    }
}
