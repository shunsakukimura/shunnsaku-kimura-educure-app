package com.example.inventory.service;

import com.example.inventory.domain.Inventory;
import com.example.inventory.domain.User; 
import com.example.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

   
    @Transactional(readOnly = true)
    public List<Inventory> findByUserId(Long userId) {
        return inventoryRepository.findByUserId(userId);
    }
    
    
    public Inventory save(Inventory inventory, User user) {
       
        inventory.setUser(user);
        return inventoryRepository.save(inventory);
    }
    
    
    public Inventory save(Inventory inventory) {
        
        return inventoryRepository.save(inventory);
    }
    
   
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }
    
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }
    
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}