package com.example.inventory.controller;

import com.example.inventory.domain.User;
import com.example.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

@Controller
public class InventoryController {

    @Autowired
    private UserService userService;

    /**
     * 在庫一覧画面を表示する
     * URL: http://localhost:8080/api/inventory
     */
    @GetMapping("/api/inventory")
    public String index(Model model) {
        // 1. 現在ログインしているユーザーの名前（Username）を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2. ユーザー名でDBからユーザー情報を取得（エラーだったfindByEmailを修正）
        Optional<User> userOptional = userService.findByUsername(username);

        // 3. ユーザーが存在すれば画面（HTML）に渡す
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        }

        // templates/inventory/index.html を表示
        return "inventory/index";
    }
}