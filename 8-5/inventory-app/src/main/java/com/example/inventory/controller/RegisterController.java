package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.inventory.domain.User;
import com.example.inventory.repository.UserRepository;

@Controller
public class RegisterController {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepository.save(user);
            return "redirect:/login?register_success";
        } catch (DataIntegrityViolationException e) {
            // 名前が重複していたらエラー画面に行かず、登録画面に戻す
            model.addAttribute("errorMessage", "そのユーザー名は既に使用されています。");
            return "register";
        }
    }
}