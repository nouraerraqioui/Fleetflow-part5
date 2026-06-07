package org.example.fleetflow.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.Repository.LivraisonRepository;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.service.interfaces.ChauffeurService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor


public class ChauffeurServiceImpl implements ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;
    private final ChauffeurMapper chauffeurMapper;
    private final LivraisonRepository livraisonRepository;
    private final LivraisonMapper livraisonMapper;
    @Override
    public ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO){
        Chauffeur chauffeur = chauffeurMapper.toEntity(chauffeurDTO);
        Chauffeur saved = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDTO(saved);
    }

    @Override
    public ChauffeurDTO modifierChauffeur(long idChauffeur , ChauffeurDTO chauffeurDTO){
        Chauffeur chauffeur = chercherChauffeurParId(idChauffeur);
        chauffeurMapper.modifierChauffeurDto(chauffeurDTO, chauffeur);
        Chauffeur saved = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDTO(saved);
    }

    @Override
    public Chauffeur chercherChauffeurParId(long idChauffeur){
        return chauffeurRepository.findById(idChauffeur).orElseThrow(() -> new RuntimeException("Chauffeur not found"));
    }

    @Override
    public Page<ChauffeurDTO> cheuffeursDisponibles(Pageable pageable){
        Page<Chauffeur> listDsipo = chauffeurRepository.findByDisponible(true,pageable);
        return listDsipo.map(chauffeurMapper::toDTO);
    }

    @Override
    public void supprimerChauffeur(long id){
        Chauffeur chauffeur = chercherChauffeurParId(id);
        chauffeurRepository.delete(chauffeur);
    }

    public Page<LivraisonDTO> livraisonsChauffeur(Long id, Pageable pageable){
        Chauffeur chauffeur = chauffeurRepository.findById(id).orElseThrow(()-> new RuntimeException("interdit"));
        Page<Livraison> pageLivraisons = livraisonRepository.findLivraisonsByChauffeur_Id(chauffeur.getId(),pageable);
        return pageLivraisons.map(livraisonMapper::toDTO);
    }
}
