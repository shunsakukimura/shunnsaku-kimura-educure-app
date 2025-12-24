
package com.example.inventory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventory.domain.Storage;
import com.example.inventory.repository.StorageRepository;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    
    public Storage findById(Long id) {
        return storageRepository.findById(id).orElse(null);
    }

    public void save(Storage storage) {
        storageRepository.save(storage);
    }

    
    public void deleteById(Long id) {
        storageRepository.deleteById(id);
    }
}
