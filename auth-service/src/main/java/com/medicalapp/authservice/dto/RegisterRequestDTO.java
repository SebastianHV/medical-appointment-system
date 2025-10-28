package com.medicalapp.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Password cannot be null")
    private  String password;
    @NotNull(message = "Role cannot be null")
    private String role;
}
