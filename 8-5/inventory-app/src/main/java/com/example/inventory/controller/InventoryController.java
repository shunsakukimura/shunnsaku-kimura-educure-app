package com.example.inventory.controller;

import com.example.inventory.domain.Inventory;
import com.example.inventory.service.InventoryService;
import com.example.inventory.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/new")
    public String newInventory(Model model) {
        model.addAttribute("inventory", new Inventory());
       
        model.addAttribute("storageList", storageRepository.findAll());
        return "inventory/new";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute Inventory inventory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storageList", storageRepository.findAll());
            return "inventory/new";
        }
        inventory.setUserId(1); 
        inventoryService.saveInventory(inventory);
        return "redirect:/api/inventory";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("inventory", inventoryService.findById(id));
        model.addAttribute("storageList", storageRepository.findAll());
        return "inventory/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Validated @ModelAttribute Inventory inventory, 
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storageList", storageRepository.findAll());
            return "inventory/edit";
        }
        inventory.setId(id);
        inventory.setUserId(1);
        inventoryService.saveInventory(inventory);
        return "redirect:/api/inventory";
    }
}