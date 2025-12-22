package com.example.inventory.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "storages")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storageName;

    // ⚠️重要：mappedByはItemクラスの変数名「storage」と一致させる
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL)
    private List<Item> items;

    public Storage() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStorageName() { return storageName; }
    public void setStorageName(String storageName) { this.storageName = storageName; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}