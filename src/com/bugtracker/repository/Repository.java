package com.bugtracker.repository;

import java.util.List;

public interface Repository<T> { // to represent many classes

    List<T> findAll(); // returning multiple objects

    T findById(int id); // return a single object 

    void save(T entity);

    void delete(int id);
}
