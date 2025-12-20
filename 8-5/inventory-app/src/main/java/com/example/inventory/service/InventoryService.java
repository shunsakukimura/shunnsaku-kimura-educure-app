package com.example.inventory.service;

import com.example.inventory.domain.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

  
    public List<Inventory> searchByItemName(String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            return inventoryRepository.findAll();
        }
        return inventoryRepository.findByItemNameContaining(itemName);
    }

    public Inventory findById(Integer id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void deleteById(Integer id) {
        inventoryRepository.deleteById(id);
    }
}