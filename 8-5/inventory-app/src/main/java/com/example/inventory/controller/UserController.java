package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.inventory.domain.User;
import com.example.inventory.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * アカウント管理画面の表示
     */
    @GetMapping("/settings")
    public String editSettings(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
        model.addAttribute("user", user);
        return "user/settings";
    }

    /**
     * アカウント情報の更新（ユーザー名・パスワード）
     */
    @PostMapping("/update")
    public String updateAccount(@RequestParam("username") String newUsername,
                                @RequestParam(value = "password", required = false) String newPassword,
                                @AuthenticationPrincipal UserDetails currentUser) {
        
        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        user.setUsername(newUsername);

        // パスワードが入力されている場合のみ暗号化して更新
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        // 保存して即座に反映
        userRepository.saveAndFlush(user);
        
        return "redirect:/login?account_updated";
    }

    /**
     * 退会処理（アカウント削除）
     * 即時削除を反映させ、再登録をスムーズにします
     */
    @PostMapping("/delete")
    public String deleteAccount(@AuthenticationPrincipal UserDetails currentUser, 
                                HttpServletRequest request, HttpServletResponse response) {
        
        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
        
        // 1. DBから削除
        userRepository.delete(user);
        
        // 2. 重要：削除を即座にDBへコミット（これで同じ名前での再登録が可能になる）
        userRepository.flush();
        
        // 3. セッションを無効化してログアウトさせる
        new SecurityContextLogoutHandler().logout(request, response, null);
        
        // 4. 完了後、ログイン画面へ
        return "redirect:/login?deleted";
    }
}