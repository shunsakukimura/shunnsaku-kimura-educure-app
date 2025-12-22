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

    // 修正: 引数の型を Integer から Long に変更
    public Storage findById(Long id) {
        return storageRepository.findById(id).orElse(null);
    }

    public void save(Storage storage) {
        storageRepository.save(storage);
    }

    // 修正: 引数の型を Integer から Long に変更
    public void deleteById(Long id) {
        storageRepository.deleteById(id);
    }
}