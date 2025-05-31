package org.example.fitnesstracker.Services;

import org.example.fitnesstracker.DTO.JwtAuthenticationResponse;
import org.example.fitnesstracker.DTO.SignInRequest;
import org.example.fitnesstracker.DTO.SignUpRequest;
import org.example.fitnesstracker.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock private UserService userService;
    @Mock private JwtService jwtService;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks private AuthenticationService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_success() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("hashedPassword")
                .build();

        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("mock-jwt-token");

        JwtAuthenticationResponse response = authService.signUp(request);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        verify(userService).create(any(User.class));
    }

    @Test
    void testSignIn_success() {
        SignInRequest request = new SignInRequest();
        request.setEmail("test@example.com");
        request.setPassword("hashedPassword");

        User user = new User();
        user.setEmail("test@example.com");

        when(userService.userDetailsService().loadUserByUsername("test@example.com")).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");

        JwtAuthenticationResponse response = authService.signIn(request);

        assertEquals("token", response.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
