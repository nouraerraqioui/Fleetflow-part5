package org.example.fleetflow.Repository;


import jakarta.validation.constraints.NotBlank;
import org.example.fleetflow.model.User;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
User findByEmail(String email);

}
