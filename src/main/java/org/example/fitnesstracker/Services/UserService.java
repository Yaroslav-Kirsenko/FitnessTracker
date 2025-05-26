package org.example.fitnesstracker.Services;


import lombok.RequiredArgsConstructor;

import org.example.fitnesstracker.Repositories.UserRepository;
import org.example.fitnesstracker.Models.User;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;


    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("user with this email already exists");
        }

        return save(user);
    }


    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    public User getCurrentUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }




}
