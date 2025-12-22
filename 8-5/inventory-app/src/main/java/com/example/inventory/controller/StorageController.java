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

    // 保存場所一覧の表示
    @GetMapping
    public String listStorages(Model model) {
        // StorageServiceのメソッド名(findAll)に合わせる
        model.addAttribute("storages", storageService.findAll());
        return "storage/list";
    }

    // 保存処理
    @PostMapping("/save")
    public String saveStorage(@ModelAttribute Storage storage) {
        // StorageServiceのメソッド名(save)に合わせる
        storageService.save(storage);
        return "redirect:/storages";
    }

    // 削除処理
    @GetMapping("/delete/{id}")
    public String deleteStorage(@PathVariable Long id) {
        // StorageServiceのメソッド名(deleteById)に合わせる
        storageService.deleteById(id);
        return "redirect:/storages";
    }
}