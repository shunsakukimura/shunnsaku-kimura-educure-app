package com.example.inventory.repository;

import java.util.Optional; // これが必要
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventory.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // 戻り値を Optional<User> にする
    Optional<User> findByUsername(String username);
}