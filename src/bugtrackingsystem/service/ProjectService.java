package bugtrackingsystem.service;

import bugtrackingsystem.model.Project;
import java.util.List;

public class ProjectService {
  private FileRepository repository;

  public ProjectService() {
    this.repository = new FileRepository();
  }

  public List<Project> getAllProjects() {
    return repository.loadProjects();
  }

  public void createProject(String name) {
    int maxId = repository.loadProjects().stream().mapToInt(Project::getId).max().orElse(0);
    Project project = new Project(maxId + 1, name);
    repository.saveProject(project);
  }

  public Project getProjectById(int id) {
    return repository.loadProjects().stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
  }
}