
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

    
    public List<Item> searchItems(User user, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return itemRepository.findByUserAndItemNameContainingIgnoreCase(user, keyword);
        }
        return itemRepository.findByUser(user);
    }

   
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}