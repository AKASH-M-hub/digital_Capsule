package com.example.timecapsule.service;

import com.example.timecapsule.entity.User;
import com.example.imecapsule.enums.Role;
import com.example.timecapsule.repository.UserRepository;
import com.example.timecapsule.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(User user) {

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        user.setRole(Role.USER);

        userRepo.save(user);
        return "Registered Successfully";
    }

    public String login(String email, String password) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(password, user.getPasswordHash())) {
            return jwtService.generateToken(email);
        }

        throw new RuntimeException("Invalid credentials");
    }
}