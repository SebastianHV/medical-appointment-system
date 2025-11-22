package com.medicalapp.authservice.service;

import com.medicalapp.authservice.dto.RegisterRequestDTO;
import com.medicalapp.authservice.dto.RegisterResponseDTO;
import com.medicalapp.authservice.entity.User;
import com.medicalapp.authservice.exception.EmailAlreadyExistsException;
import com.medicalapp.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j // Lombok annotation for logging
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional // Ensures that the operation is atomic
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
        log.info("Attempting to register user with email: {}", request.getEmail());
//        Check if email already exists
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    log.error("Registration failed: Email {} already exists", request.getEmail());
                    throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
                });

        User user = User.builder()
                .email(request.getEmail().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered successfully with email: {} and ID: {}", savedUser.getEmail(), savedUser.getId());

//        Convert to RegisterResponseDTO
        return RegisterResponseDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}
