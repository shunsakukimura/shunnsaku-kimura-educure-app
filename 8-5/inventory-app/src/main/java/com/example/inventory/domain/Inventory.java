package com.example.inventory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "items")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @NotNull(message = "保存場所を選択してください")
    private Integer storageId;

    @NotBlank(message = "商品名を入力してください")
    private String itemName;

    @NotNull(message = "数量を入力してください")
    @Min(value = 0, message = "数量は0以上の値を入力してください")
    private Integer quantity;

    private LocalDate expirationDate;

   
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getStorageId() { return storageId; }
    public void setStorageId(Integer storageId) { this.storageId = storageId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public String getStorageName() {
        if (this.storageId == null) return "";
        return switch (this.storageId) {
            case 1 -> "冷蔵庫";
            case 2 -> "冷凍庫";
            case 3 -> "野菜室";
            case 4 -> "常温パントリー";
            default -> "不明";
        };
    }
}