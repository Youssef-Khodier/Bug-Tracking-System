package com.bugtracker.main;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.Bug.BugStatus;
import com.bugtracker.repository.BugRepository;
import com.bugtracker.repository.UserRepository;
import com.bugtracker.service.EmailService;
import com.bugtracker.util.DataSeeder;

public class Main {

    public static void main(String[] args) {
        // Seed initial admin user if needed
        DataSeeder.seedUsers();

        // Test user repository
        UserRepository userRepo = new UserRepository();
        System.out.println("Current users: " + userRepo.findAll().size());

        // Test bug repository
        BugRepository bugRepo = new BugRepository();
        Bug testBug = new Bug(
                1,
                "Sample Bug",
                "This is a sample bug for testing persistence.",
                "UI",
                "HIGH",
                "CRITICAL",
                "Sample Project",
                "2025-12-01",
                BugStatus.OPEN,
                1,
                1,
                null
        );
        bugRepo.save(testBug);
        System.out.println("Current bugs: " + bugRepo.findAll().size());

        // Test email service
        EmailService emailService = new EmailService();
        emailService.sendEmail("admin@example.com", "Test Email", "This is a test email log entry.");
        System.out.println("Email log entry written.");
    }
}
