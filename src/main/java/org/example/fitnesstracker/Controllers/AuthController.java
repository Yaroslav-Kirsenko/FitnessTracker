package org.example.fitnesstracker.Controllers;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.example.fitnesstracker.DTO.JwtAuthenticationResponse;
import org.example.fitnesstracker.DTO.SignInRequest;
import org.example.fitnesstracker.DTO.SignUpRequest;
import org.example.fitnesstracker.Services.AuthenticationService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
