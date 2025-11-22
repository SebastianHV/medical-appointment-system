package com.medicalapp.authservice.controller;

import com.medicalapp.authservice.dto.RegisterRequestDTO;
import com.medicalapp.authservice.dto.RegisterResponseDTO;
import com.medicalapp.authservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth") // Base path for authentication endpoints
public class AuthController {

    //    Constructor injection of UserService
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //  register POST endpoint
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
//        Remove try-catch block for cleaner error handling
//        Letting GlobalExceptionHandler handle exceptions
        RegisterResponseDTO response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}