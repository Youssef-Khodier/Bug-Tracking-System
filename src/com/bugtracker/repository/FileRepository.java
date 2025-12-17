package com.bugtracker.repository;

import java.io.*;
import java.util.*;

public abstract class FileRepository<T> implements Repository<T> {

    protected String filename;

    public FileRepository(String filename) {
        this.filename = filename;
        createFileIfMissing();
    }

    private void createFileIfMissing() {
        try {
            File file = new File(filename);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create file: " + filename);
        }
    }

    protected List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null)
                lines.add(line);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filename);
        }
        return lines;
    }

    protected void writeLines(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + filename);
        }
    }
}

