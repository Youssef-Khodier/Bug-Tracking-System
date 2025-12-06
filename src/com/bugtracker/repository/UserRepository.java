package com.bugtracker.repository;

import com.bugtracker.entity.User;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class UserRepository extends FileRepository<User> {

    public UserRepository() {
        super("data/users.json", new TypeToken<List<User>>() {}.getType());
    }
}
