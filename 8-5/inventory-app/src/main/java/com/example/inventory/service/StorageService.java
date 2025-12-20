package com.example.inventory.service;

import com.example.inventory.domain.Storage;
import com.example.inventory.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    
    public List<Storage> getAllStorages() {
        return storageRepository.findAll();
    }

    
    public Storage getStorageById(Integer id) {
        return storageRepository.findById(id).orElse(null);
    }

    
    public Storage saveStorage(Storage storage) {
        return storageRepository.save(storage);
    }

    
    public void deleteStorage(Integer id) {
        storageRepository.deleteById(id);
    }
}