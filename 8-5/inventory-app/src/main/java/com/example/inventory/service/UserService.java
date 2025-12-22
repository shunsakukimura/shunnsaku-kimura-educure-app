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
        // 保存する直前に、パスワードを暗号化（ハッシュ化）する
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        // ロールを明示的にセット
        user.setRole("ROLE_USER");
        
        userRepository.save(user);
        System.out.println("DEBUG: ユーザーを保存しました。パスワードは暗号化されています。");
    }
}