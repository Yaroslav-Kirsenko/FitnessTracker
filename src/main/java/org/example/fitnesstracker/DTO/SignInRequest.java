package org.example.fitnesstracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SignInRequest {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "The email must be in the format user@example.com")
    private String email;

    @Size(min = 8, max = 255, message = "Password length must be at least 8 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}

