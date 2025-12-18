package com.example.inventory.service;

import com.example.inventory.domain.Storage;
import com.example.inventory.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Storage> findById(Long id) {
        return storageRepository.findById(id); 
    }

    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }
}