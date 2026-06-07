package org.example.fleetflow.Interfaces;

import org.example.fleetflow.DTO.LoginRequestDTO;
import org.example.fleetflow.DTO.RegisterRequestDTO;

public interface AuthService {

    String register(RegisterRequestDTO request);

    String login(LoginRequestDTO request);
}
