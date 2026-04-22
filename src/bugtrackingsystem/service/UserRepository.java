package bugtrackingsystem.service;

import bugtrackingsystem.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Repository for Users.
 */
public class UserRepository implements Repository<User> {
  private static final String DATA_DIR = "data";
  private static final String FILE_PATH = DATA_DIR + File.separator + "users.txt";

  public UserRepository() {
    File directory = new File(DATA_DIR);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  @Override
  public User getById(int id) {
    return getAll().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    File file = new File(FILE_PATH);
    if (!file.exists())
      return users;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length >= 4) {
          try {
            User user = new User();
            user.setId(Integer.parseInt(parts[0]));
            user.setUsername(parts[1]);
            user.setPassword(parts[2]);
            user.setRole(parts[3]);
            users.add(user);
          } catch (NumberFormatException e) {

            System.err.println("Skipping invalid user line: " + line);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public void save(User user) {
    List<User> users = getAll();
    boolean updated = false;
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId() == user.getId()) {
        users.set(i, user);
        updated = true;
        break;
      }
    }
    if (!updated) {
      users.add(user);
    }
    saveAll(users);
  }

  @Override
  public void delete(int id) {
    List<User> users = getAll();
    users.removeIf(u -> u.getId() == id);
    saveAll(users);
  }

  private void saveAll(List<User> users) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (User u : users) {
        bw.write(u.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
