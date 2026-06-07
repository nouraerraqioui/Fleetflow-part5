package org.example.fleetflow.mapper;


import org.example.fleetflow.DTO.RegisterRequestDTO;
import org.example.fleetflow.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toEntity(RegisterRequestDTO userDTO);


}
