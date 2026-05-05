package com.example.timecapsule.controller;

import com.example.timecapsule.entity.User;
import com.example.timecapsule.service.AuthService;
import org.example.springframework.beans.factory.annotation.Autowired;
import org.example.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        return authService.login(email, password);
    }
}