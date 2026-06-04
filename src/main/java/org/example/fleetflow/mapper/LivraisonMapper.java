package org.example.fleetflow.mapper;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LivraisonMapper {

   @Mapping(target = "chauffeur", ignore = true)
   @Mapping(target = "client", ignore = true)
   @Mapping(target = "vehicule", ignore = true)
   Livraison toEntity(LivraisonDTO dto);

   @Mapping(source = "chauffeur.id", target = "chauffeurId")
   @Mapping(source = "client.id", target = "clientId")
   @Mapping(source = "vehicule.id", target = "vehiculeId")
   LivraisonDTO toDTO(Livraison livraison);

   List<LivraisonDTO> toDTOList(List<Livraison> listLivraisons);
}
