package bugtrackingsystem.service;

import bugtrackingsystem.model.User;
import java.util.List;

public class AuthService {
  private FileRepository repository;

  public AuthService() {
    this.repository = new FileRepository();
  }

  public User login(String username, String password) {
    List<User> users = repository.loadUsers();
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }
}
