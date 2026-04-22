/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugtrackingsystem.controller;

/**
 *
 * @author Y416
 */
import bugtrackingsystem.model.User;
import bugtrackingsystem.service.AuthService;

public class LoginController {
  private AuthService authService;

  public LoginController() {
    this.authService = new AuthService();
  }

  public User login(String username, String password) {
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      return null;
    }
    return authService.login(username, password);
  }
}
