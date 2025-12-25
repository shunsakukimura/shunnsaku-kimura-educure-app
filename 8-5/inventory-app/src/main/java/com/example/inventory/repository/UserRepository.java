package com.example.inventory.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
// ★重要：entityではなくdomainを指定して赤エラーを消します
import com.example.inventory.domain.User; 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ユーザー名で検索するメソッド
    Optional<User> findByUsername(String username);

    // ★追加：退会機能のためにユーザー名で削除を実行する
    @Transactional
    void deleteByUsername(String username);
}