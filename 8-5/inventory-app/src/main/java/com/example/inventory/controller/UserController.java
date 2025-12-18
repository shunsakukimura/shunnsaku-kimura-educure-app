package com.example.inventory.controller;

import com.example.inventory.domain.User;
import com.example.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 新規登録画面の表示
    @GetMapping("/api/users/register")
    public String registerForm(@ModelAttribute User user) {
        return "register";
    }

    // ★重要：新規登録処理
    @PostMapping("/api/users/register")
    public String register(@ModelAttribute User user) {
        // パスワードを暗号化して保存（UserService側のsaveを呼ぶ）
        userService.save(user);
        
        // 登録が終わったらログイン画面へリダイレクト
        return "redirect:/login";
    }
}