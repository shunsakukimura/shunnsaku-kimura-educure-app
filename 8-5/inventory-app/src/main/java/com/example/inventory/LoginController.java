package com.example.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        // templates/login.html を表示しなさいという命令
        // ここで "redirect:/login" と書くとループするので注意！
        return "login"; 
    }
}