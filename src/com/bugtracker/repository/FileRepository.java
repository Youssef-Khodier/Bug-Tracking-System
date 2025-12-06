package com.bugtracker.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T> implements Repository<T> {

    protected final Gson gson;
    protected final File file;
    protected final Type type;

    public FileRepository(String filePath, Type type) {
        this.file = new File(filePath);
        this.type = type;
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        this.gson = builder.create();

        File parent = this.file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    @Override
    public List<T> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            List<T> items = gson.fromJson(reader, type);
            if (items == null) {
                return new ArrayList<>();
            }
            return items;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public T findById(int id) {
        List<T> items = findAll();
        for (T item : items) {
            try {
                Method getIdMethod = item.getClass().getMethod("getId");
                Object value = getIdMethod.invoke(item);
                if (value instanceof Integer && (Integer) value == id) {
                    return item;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void save(T item) {
        List<T> allItems = findAll();
        int itemId = -1;
        try {
            Method getIdMethod = item.getClass().getMethod("getId");
            Object value = getIdMethod.invoke(item);
            if (value instanceof Integer) {
                itemId = (Integer) value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean found = false;
        int index = -1;
        for (int i = 0; i < allItems.size(); i++) {
            T existing = allItems.get(i);
            try {
                Method getIdMethod = existing.getClass().getMethod("getId");
                Object value = getIdMethod.invoke(existing);
                if (value instanceof Integer && (Integer) value == itemId) {
                    found = true;
                    index = i;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (found && index >= 0) {
            allItems.set(index, item);
        } else {
            allItems.add(item);
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(allItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<T> allItems = findAll();
        T itemToRemove = null;

        for (T existing : allItems) {
            try {
                Method getIdMethod = existing.getClass().getMethod("getId");
                Object value = getIdMethod.invoke(existing);
                if (value instanceof Integer && (Integer) value == id) {
                    itemToRemove = existing;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (itemToRemove != null) {
            allItems.remove(itemToRemove);
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(allItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
