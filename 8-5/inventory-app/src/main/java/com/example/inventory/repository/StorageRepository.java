
package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventory.domain.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
