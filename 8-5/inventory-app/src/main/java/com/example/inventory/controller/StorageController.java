package com.example.inventory.controller;

import com.example.inventory.domain.Storage;
import com.example.inventory.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

   
    @GetMapping
    public String list(Model model) {
        
        List<Storage> storages = storageService.getAllStorages();
        model.addAttribute("storages", storages);
        return "storage/list";
    }

    
    @PostMapping("/save")
    public String save(@ModelAttribute Storage storage) {
        
        storageService.saveStorage(storage);
        return "redirect:/api/storages";
    }

    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        
        storageService.deleteStorage(id);
        return "redirect:/api/storages";
    }
}