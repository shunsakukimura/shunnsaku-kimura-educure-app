package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// ★重要：entityではなくdomainを指定
import com.example.inventory.domain.User; 
import com.example.inventory.repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 新規登録画面の表示
    @GetMapping("/register")
    public String showRegisterForm(@ModelAttribute User user) {
        return "register";
    }

    // 新規登録の実行
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/login";
    }

    // ★追加：設定ボタン（/user/edit）の404エラーを解消する
    @GetMapping("/user/edit")
    public String editUser(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
        model.addAttribute("user", user);
        return "user_edit";
    }

    // ★追加：退会処理（アカウント削除）
    @PostMapping("/user/delete")
    public String deleteUser(Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            userRepository.deleteByUsername(username);
            // セッションを破棄してログアウトさせる
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login?deleted";
    }
}