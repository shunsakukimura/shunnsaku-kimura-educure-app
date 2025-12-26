package com.example.inventory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "食材名を入力してください")
    private String itemName;

    @NotNull(message = "数量を入力してください")
    @Min(value = 1, message = "1以上の数値を入力してください")
    private Integer quantity;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}