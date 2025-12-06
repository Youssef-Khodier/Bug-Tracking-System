package com.bugtracker.util;

import com.bugtracker.entity.User;
import com.bugtracker.entity.User.UserRole;
import com.bugtracker.repository.UserRepository;

public class DataSeeder {

    public static void seedUsers() {
        UserRepository repo = new UserRepository();
        if (repo.findAll().isEmpty()) {
            User admin = new User(1, "admin", "admin123", UserRole.ADMIN);
            repo.save(admin);
            System.out.println("Admin user seeded.");
        }
    }
}




