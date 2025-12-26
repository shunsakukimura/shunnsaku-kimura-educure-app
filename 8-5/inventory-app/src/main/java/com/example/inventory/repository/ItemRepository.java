package com.example.inventory.repository;

import com.example.inventory.domain.Item;
import com.example.inventory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    
    List<Item> findByUser(User user);
    
    
    List<Item> findByUserAndItemNameContainingIgnoreCase(User user, String itemName);
}