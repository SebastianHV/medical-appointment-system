package com.medicalapp.authservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponseDTO {
    private Long id;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
