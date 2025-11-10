package com.medicalapp.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private Long id;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
