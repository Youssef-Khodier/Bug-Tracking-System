package bugtrackingsystem.service;

import bugtrackingsystem.model.Bug;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Repository for Bugs.
 */
public class BugRepository implements Repository<Bug> {
  private static final String DATA_DIR = "data";
  private static final String FILE_PATH = DATA_DIR + File.separator + "bugs.txt";

  public BugRepository() {
    File directory = new File(DATA_DIR);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  @Override
  public Bug getById(int id) {
    return getAll().stream().filter(b -> b.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Bug> getAll() {
    List<Bug> bugs = new ArrayList<>();
    File file = new File(FILE_PATH);
    if (!file.exists())
      return bugs;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length >= 8) {
          try {
            Bug bug = new Bug();
            bug.setId(Integer.parseInt(parts[0]));
            bug.setTitle(parts[1]);
            bug.setDescription(parts[2]);
            bug.setStatus(parts[3]);
            bug.setPriority(parts[4]);
            bug.setReporterId(Integer.parseInt(parts[5]));
            bug.setAssigneeId(Integer.parseInt(parts[6]));
            bug.setProjectId(Integer.parseInt(parts[7]));
            bug.setProjectId(Integer.parseInt(parts[7]));
            if (parts.length >= 9)
              bug.setType(parts[8]);
            if (parts.length >= 10)
              bug.setLevel(parts[9]);
            if (parts.length >= 11)
              bug.setDate(parts[10]);
            if (parts.length >= 12)
              bug.setScreenshotPath(parts[11]);
            bugs.add(bug);
          } catch (NumberFormatException e) {
            System.err.println("Skipping invalid bug line: " + line);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bugs;
  }

  @Override
  public void save(Bug bug) {
    List<Bug> bugs = getAll();
    boolean updated = false;
    for (int i = 0; i < bugs.size(); i++) {
      if (bugs.get(i).getId() == bug.getId()) {
        bugs.set(i, bug);
        updated = true;
        break;
      }
    }
    if (!updated) {
      bugs.add(bug);
    }
    saveAll(bugs);
  }

  @Override
  public void delete(int id) {
    List<Bug> bugs = getAll();
    bugs.removeIf(b -> b.getId() == id);
    saveAll(bugs);
  }

  private void saveAll(List<Bug> bugs) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (Bug b : bugs) {
        bw.write(b.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
