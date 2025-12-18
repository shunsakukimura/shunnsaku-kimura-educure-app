package com.example.inventory.repository;

import com.example.inventory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // ユーザー名で検索するための定義
    Optional<User> findByUsername(String username);
}