package com.example.inventory.service;

import com.example.inventory.domain.User;
import com.example.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // パスワードを暗号化して保存する
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // エラーが出ていた「findByEmail」の代わりにこれを使います
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}