package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.inventory.domain.User;
import com.example.inventory.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 1. 画面を表示するための設定 (GET)
    @GetMapping("/api/users/signup")
    public String signupForm() {
        // "signup" と返すと src/main/resources/templates/signup.html を探しに行きます
        return "signup"; 
    }

    // 2. 登録ボタンが押された時の処理 (POST)
    @PostMapping("/api/users/signup")
    public String signup(User user) {
        // すでにユーザー名が存在するかチェック
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/api/users/signup?error";
        }
        
        // 権限を設定して保存
        user.setRole("USER");
        userService.save(user);
        
        // 成功したらログイン画面へ
        return "redirect:/login?signup_success";
    }
}