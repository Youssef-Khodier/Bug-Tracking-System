package com.bugtracker.repository;

import com.bugtracker.entity.User;
import com.bugtracker.entity.User.UserRole;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepository extends FileRepository<User> {

    public UserRepository() {
        super("users.txt");
    }

    @Override
    public List<User> findAll() {
        List<String> lines = readLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty())
                users.add(fromFileString(line));
        }
        return users;
    }

    @Override
    public void save(User user) {
        List<User> users = findAll();
        users.add(user);
        updateAll(users);
    }

    @Override
    public void updateAll(List<User> users) {
        List<String> lines = users.stream().map(this::toFileString).collect(Collectors.toList());
        writeLines(lines);
    }

    public Optional<User> findById(int id) {
        return findAll().stream().filter(u -> u.getId() == id).findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return findAll().stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public void deleteUser(int id) {
        List<User> users = findAll();
        users.removeIf(u -> u.getId() == id);
        updateAll(users);
    }

    // Serialization
    private String toFileString(User u) {
        return u.getId() + "|" + u.getUsername() + "|" + u.getPassword() + "|" + u.getRole().name();
    }

    private User fromFileString(String line) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0]);
        String username = parts[1];
        String password = parts[2];
        UserRole role = UserRole.valueOf(parts[3]);
        return new User(id, username, password, role);
    }
}


