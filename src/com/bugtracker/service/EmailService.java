package com.bugtracker.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class EmailService {

    public void sendEmail(String to, String subject, String body) {
        String logMessage = String.format(
                "[%s] To: %s | Subject: %s | Body: %s",
                new Date().toString(),
                to,
                subject,
                body
        );

        try (FileWriter writer = new FileWriter("data/email_logs.txt", true)) {
            writer.write(logMessage);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
