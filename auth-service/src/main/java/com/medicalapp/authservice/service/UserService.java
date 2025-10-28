package com.medicalapp.authservice.service;

import com.medicalapp.authservice.dto.RegisterRequestDTO;
import com.medicalapp.authservice.dto.RegisterResponseDTO;
import com.medicalapp.authservice.entity.User;
import com.medicalapp.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
//        Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
//        Hashing the password before saving
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setRole(user.getRole());
        return response;


    }
}
