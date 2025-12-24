
package com.example.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.inventory.domain.Storage;
import com.example.inventory.domain.User;
import com.example.inventory.repository.StorageRepository;
import com.example.inventory.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        
        if (storageRepository.count() == 0) {
            saveStorage("冷蔵庫");
            saveStorage("冷凍庫");
            saveStorage("食品保管庫");
            saveStorage("常温置き場（弁当など）");
            System.out.println("✅ 保存場所を登録しました。");
        }

        String myUsername = "shunk"; 
        if (userRepository.findByUsername(myUsername) == null) {
            User user = new User();
            user.setUsername(myUsername);
           
            user.setPassword(passwordEncoder.encode("myPassword123"));
            user.setRole("ROLE_ADMIN");
            user.setEmail("user@example.com");
            userRepository.save(user);

            System.out.println("✅ アカウント「" + myUsername + "」を登録しました。");
        }
    }

    private void saveStorage(String name) {
        Storage s = new Storage();
        s.setStorageName(name);
        storageRepository.save(s);
    }
}
