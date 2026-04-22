package bugtrackingsystem.service;

import bugtrackingsystem.model.Bug;
import java.util.List;
import java.util.stream.Collectors;

public class BugService {
  private bugtrackingsystem.service.Repository<Bug> repository;

  public BugService() {
    this.repository = new bugtrackingsystem.service.BugRepository();
  }

  public List<Bug> getAllBugs() {
    return repository.getAll();
  }

  public List<Bug> getBugsByAssignee(int assigneeId) {
    return repository.getAll().stream()
        .filter(b -> b.getAssigneeId() == assigneeId)
        .collect(Collectors.toList());
  }

  public List<Bug> getBugsByReporter(int reporterId) {
    return repository.getAll().stream()
        .filter(b -> b.getReporterId() == reporterId)
        .collect(Collectors.toList());
  }

  public List<Bug> getBugsByProject(int projectId) {
    return repository.getAll().stream()
        .filter(b -> b.getProjectId() == projectId)
        .collect(Collectors.toList());
  }

  public void createBug(Bug bug) {
    
    if (bug.getId() == 0) {
      int maxId = repository.getAll().stream().mapToInt(Bug::getId).max().orElse(0);
      bug.setId(maxId + 1);
    }
    repository.save(bug);
    System.out.println("Email Notification: New bug reported - " + bug.getTitle());
  }

  public void updateBug(Bug bug) {
    repository.save(bug);
    if ("Completed".equalsIgnoreCase(bug.getStatus())) {
      System.out.println("Email Notification: Bug marked as Completed - " + bug.getTitle());
    } else {
      System.out.println("Email Notification: Bug updated - " + bug.getTitle());
    }
  }
}
