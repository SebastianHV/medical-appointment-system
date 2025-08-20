package com.medicalapp.authservice.repository;

import com.medicalapp.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<User, Long>  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
