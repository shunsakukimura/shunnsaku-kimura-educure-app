package com.example.inventory.repository;

import com.example.inventory.domain.Item;
import com.example.inventory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    // ユーザーに関連する全アイテムを取得
    List<Item> findByUser(User user);
    
    // ユーザーに関連し、かつ名前にキーワードを含むアイテムを検索（大文字小文字を区別しない）
    List<Item> findByUserAndItemNameContainingIgnoreCase(User user, String itemName);
}