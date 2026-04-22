package bugtrackingsystem.controller;

import bugtrackingsystem.model.User;
import bugtrackingsystem.service.FileRepository;
import java.util.List;

public class UserController {
  private bugtrackingsystem.service.Repository<User> repository;

  public UserController() {
    this.repository = new bugtrackingsystem.service.UserRepository();
  }

  public List<User> getAllUsers() {
    return repository.getAll();
  }

  public void addUser(String username, String password, String role) {
    // Simple ID generation
    List<User> users = repository.getAll();
    int maxId = users.stream().mapToInt(User::getId).max().orElse(0);
    User user = new User(maxId + 1, username, password, role);
    repository.save(user);
  }

  public void updateUser(int id, String username, String password, String role) {
    User user = new User(id, username, password, role);
    repository.save(user);
  }

  public void deleteUser(int id) {
    repository.delete(id);
  }
}
