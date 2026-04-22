package bugtrackingsystem.service;

import bugtrackingsystem.model.Bug;
import bugtrackingsystem.model.Project;
import bugtrackingsystem.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
  private static final String DATA_DIR = "data";
  private static final String USERS_FILE = DATA_DIR + File.separator + "users.txt";
  private static final String BUGS_FILE = DATA_DIR + File.separator + "bugs.txt";
  private static final String PROJECTS_FILE = DATA_DIR + File.separator + "projects.txt";

  public FileRepository() {
    File directory = new File(DATA_DIR);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  // --- Users ---
  public List<User> loadUsers() {
    List<User> users = new ArrayList<>();
    File file = new File(USERS_FILE);
    if (!file.exists())
      return users;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length >= 4) {
          User user = new User();
          user.setId(Integer.parseInt(parts[0]));
          user.setUsername(parts[1]);
          user.setPassword(parts[2]);
          user.setRole(parts[3]);
          users.add(user);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }

  public void saveUser(User user) {
    List<User> users = loadUsers();
    boolean updated = false;
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId() == user.getId()) {
        users.set(i, user);
        updated = true;
        break;
      }
    }
    if (!updated) {
      users.add(user);
    }
    saveAllUsers(users);
  }

  public void deleteUser(int userId) {
    List<User> users = loadUsers();
    users.removeIf(u -> u.getId() == userId);
    saveAllUsers(users);
  }

  private void saveAllUsers(List<User> users) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
      for (User u : users) {
        bw.write(u.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // --- Bugs ---
  public List<Bug> loadBugs() {
    List<Bug> bugs = new ArrayList<>();
    File file = new File(BUGS_FILE);
    if (!file.exists())
      return bugs;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        
        String[] parts = line.split("\\|");
        if (parts.length >= 8) {
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
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bugs;
  }

  public void saveBug(Bug bug) {
    List<Bug> bugs = loadBugs();
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
    saveAllBugs(bugs);
  }

  private void saveAllBugs(List<Bug> bugs) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(BUGS_FILE))) {
      for (Bug b : bugs) {
        bw.write(b.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // --- Projects ---
  public List<Project> loadProjects() {
    List<Project> projects = new ArrayList<>();
    File file = new File(PROJECTS_FILE);
    if (!file.exists())
      return projects;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length >= 2) {
          Project p = new Project();
          p.setId(Integer.parseInt(parts[0]));
          p.setName(parts[1]);
          projects.add(p);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return projects;
  }

  public void saveProject(Project project) {
    List<Project> projects = loadProjects();
    boolean updated = false;
    for (int i = 0; i < projects.size(); i++) {
      if (projects.get(i).getId() == project.getId()) {
        projects.set(i, project);
        updated = true;
        break;
      }
    }
    if (!updated) {
      projects.add(project);
    }
    saveAllProjects(projects);
  }

  private void saveAllProjects(List<Project> projects) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(PROJECTS_FILE))) {
      for (Project p : projects) {
        bw.write(p.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
