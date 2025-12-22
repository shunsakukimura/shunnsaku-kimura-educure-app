package com.example.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * ログイン画面を表示する
     */
    @GetMapping("/login")
    public String login() {
        // templates フォルダの中の login.html を探しに行きます
        return "login";
    }
}