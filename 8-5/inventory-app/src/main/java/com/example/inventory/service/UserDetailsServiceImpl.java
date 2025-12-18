package com.example.inventory.service;

import com.example.inventory.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections; 

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Spring Securityがユーザーを認証するために呼び出すメソッド。
     * @param username フォームから入力されたユーザー名
     * @return UserDetailsインターフェースの実装 (Spring SecurityのUserオブジェクト)
     * @throws UsernameNotFoundException ユーザーが見つからなかった場合
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザー名が見つかりません: " + username));

      
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() 
        );
    }
}