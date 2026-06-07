package org.example.fleetflow.controller;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.LoginRequestDTO;
import org.example.fleetflow.DTO.RegisterRequestDTO;
import org.example.fleetflow.Interfaces.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationLoginController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO authRequest) {
        return authService.login(authRequest);
    }
}