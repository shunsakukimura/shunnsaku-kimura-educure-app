package com.example.inventory.controller;

import com.example.inventory.domain.Storage;
import com.example.inventory.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;


@RestController
@RequestMapping("/api/storages")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

   
    @GetMapping
    public List<Storage> getAllStorages() {
        return storageService.findAll();
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Storage createStorage(@RequestBody Storage storage) {
        storage.setId(null); 
        return storageService.save(storage);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Storage> getStorageById(@PathVariable Long id) {
        return storageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
}