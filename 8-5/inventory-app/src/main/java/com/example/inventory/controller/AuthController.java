package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.inventory.domain.User; 
import com.example.inventory.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/register")
    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "redirect:/register?error";
        }
        userRepository.save(user);
        return "redirect:/login";
    }
}