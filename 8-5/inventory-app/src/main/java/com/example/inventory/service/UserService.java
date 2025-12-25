package com.example.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.inventory.domain.User;
import com.example.inventory.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        
        user.setRole("ROLE_USER");
        
        userRepository.save(user);
        System.out.println("DEBUG: ユーザーを保存しました。パスワードは暗号化されています。");
    }
}