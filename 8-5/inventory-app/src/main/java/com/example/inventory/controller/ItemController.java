package com.example.inventory.controller;

import com.example.inventory.domain.Item;
import com.example.inventory.domain.User;
import com.example.inventory.service.ItemService;
import com.example.inventory.repository.UserRepository;
import com.example.inventory.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService; 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword, 
                        Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        
        
        List<Item> items = itemService.searchItems(user, keyword);
        
        model.addAttribute("items", items);
        model.addAttribute("keyword", keyword);
        return "inventory/index";
    }

    @GetMapping("/new")
    public String newItem(@ModelAttribute Item item, Model model) {
        model.addAttribute("storages", storageRepository.findAll());
        return "inventory/new";
    }

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute Item item, BindingResult result, 
                         Authentication authentication, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storages", storageRepository.findAll());
            return "inventory/new";
        }
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        item.setUser(user);
        itemService.saveItem(item); 
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id); 
        model.addAttribute("item", item);
        model.addAttribute("storages", storageRepository.findAll());
        return "inventory/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateItem(@PathVariable Long id, @Validated @ModelAttribute Item item, 
                             BindingResult result, Authentication authentication, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storages", storageRepository.findAll());
            return "inventory/edit";
        }
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        item.setUser(user);
        item.setId(id);
        itemService.saveItem(item); 
        return "redirect:/items";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id); 
        return "redirect:/items";
    }
}