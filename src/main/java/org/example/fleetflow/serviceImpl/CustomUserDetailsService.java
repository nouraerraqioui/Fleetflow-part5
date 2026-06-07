package org.example.fleetflow.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.Repository.UserRepository;

import org.example.fleetflow.model.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("utilisateur introuvable");
        }
        return user;
    }
}
