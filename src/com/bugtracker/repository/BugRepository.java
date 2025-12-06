package com.bugtracker.repository;

import com.bugtracker.entity.Bug;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class BugRepository extends FileRepository<Bug> {

    public BugRepository() {
        super("data/bugs.json", new TypeToken<List<Bug>>() {}.getType());
    }
}
