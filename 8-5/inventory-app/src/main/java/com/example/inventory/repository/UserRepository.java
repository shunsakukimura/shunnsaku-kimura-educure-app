package com.example.inventory.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventory.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
    Optional<User> findByUsername(String username);
}