package com.example.inventory.service;

import com.example.inventory.domain.Item;
import com.example.inventory.domain.User;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // 一覧・検索用
    public List<Item> searchItems(User user, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return itemRepository.findByUserAndItemNameContainingIgnoreCase(user, keyword);
        }
        return itemRepository.findByUser(user);
    }

    // IDで1件取得用 (Controllerの getItemById に対応)
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // 保存・更新用 (Controllerの saveItem に対応)
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 削除用 (Controllerの deleteItem に対応)
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}