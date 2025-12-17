package com.bugtracker.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    void save(T item);
    void updateAll(List<T> items);
}
