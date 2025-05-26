package org.example.fitnesstracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.fitnesstracker.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
