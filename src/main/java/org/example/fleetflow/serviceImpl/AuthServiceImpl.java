package org.example.fleetflow.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.Configuration.JwtUtils;
import org.example.fleetflow.DTO.LoginRequestDTO;
import org.example.fleetflow.DTO.RegisterRequestDTO;
import org.example.fleetflow.Interfaces.AuthService;

import org.example.fleetflow.Repository.UserRepository;
import org.example.fleetflow.mapper.UserMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Role;
import org.example.fleetflow.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @Override
    public String register(RegisterRequestDTO request) {
        User user;

        // if CHAUFFEUR → create Chauffeur entity
        if (request.getRole() == Role.CHAUFFEUR) {

            Chauffeur chauffeur = new Chauffeur();
            chauffeur.setNom(request.getNom());
            chauffeur.setTelephone(request.getTelephone());
            chauffeur.setPermisType(request.getPermisType());
            chauffeur.setDisponible(true);

            user = chauffeur;

        } else {
            user = new User();
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        // JWT GENERATION
        return jwtUtils.generateToken(user);
    }


    @Override
    public String login(LoginRequestDTO request) {

        // authenticate (Spring Security handles password check)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("utilisateur introuvable");
        }
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("mot de passe incorrect");
        }
        return jwtUtils.generateToken(user);
    }
}