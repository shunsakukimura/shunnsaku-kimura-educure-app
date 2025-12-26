
package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.inventory.domain.Storage;
import com.example.inventory.service.StorageService;

@Controller
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

    
    @GetMapping
    public String listStorages(Model model) {
        
        model.addAttribute("storages", storageService.findAll());
        return "storage/list";
    }

    
    @PostMapping("/save")
    public String saveStorage(@ModelAttribute Storage storage) {
        
        storageService.save(storage);
        return "redirect:/storages";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteStorage(@PathVariable Long id) {
        
        storageService.deleteById(id);
        return "redirect:/storages";
    }
}