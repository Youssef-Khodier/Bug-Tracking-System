package bugtrackingsystem.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailService {
  private static final String EMAIL_FILE = "data/email.txt";

  public void sendEmail(String recipientRole, String subject, String body) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMAIL_FILE, true))) {
      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      writer.write("--------------------------------------------------");
      writer.newLine();
      writer.write("Time: " + timestamp);
      writer.newLine();
      writer.write("To: " + recipientRole); // In real app, this would be an email address
      writer.newLine();
      writer.write("Subject: " + subject);
      writer.newLine();
      writer.write("Body: " + body);
      writer.newLine();
      writer.write("--------------------------------------------------");
      writer.newLine();
      writer.newLine();
    } catch (IOException e) {
      System.err.println("Error writing to email file: " + e.getMessage());
    }
  }
}
