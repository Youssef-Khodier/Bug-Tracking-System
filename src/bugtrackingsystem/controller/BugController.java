package bugtrackingsystem.controller;

import bugtrackingsystem.model.Bug;
import bugtrackingsystem.service.BugService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class BugController {
  private BugService bugService;
  private bugtrackingsystem.service.EmailService emailService;

  public BugController() {
    this.bugService = new BugService();
    this.emailService = new bugtrackingsystem.service.EmailService();
  }

  public List<Bug> getAllBugs() {
    return bugService.getAllBugs();
  }

  public List<Bug> getBugsByAssignee(int assigneeId) {
    return bugService.getBugsByAssignee(assigneeId);
  }

  public List<Bug> getBugsByReporter(int reporterId) {
    return bugService.getBugsByReporter(reporterId);
  }

  public void createBug(String title, String desc, String priority, int reporterId, int projectId, String type,
      String level, String date, String status, File screenshotFile) {
    Bug bug = new Bug();
    bug.setTitle(title);
    bug.setDescription(desc);
    bug.setPriority(priority);
    bug.setReporterId(reporterId);
    bug.setProjectId(projectId);
    bug.setType(type);
    bug.setLevel(level);
    bug.setDate(date);
    bug.setStatus(status != null ? status : "Open");
    bug.setAssigneeId(0); // Unassigned

    if (screenshotFile != null && screenshotFile.exists()) {
      try {
        String screenshotsDir = "data" + File.separator + "screenshots";
        File dir = new File(screenshotsDir);
        if (!dir.exists())
          dir.mkdirs();

        String newFileName = System.currentTimeMillis() + "_" + screenshotFile.getName();
        File destFile = new File(dir, newFileName);
        Files.copy(screenshotFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        bug.setScreenshotPath(destFile.getAbsolutePath());
      } catch (IOException e) {
        System.err.println("Failed to save screenshot: " + e.getMessage());
      }
    }

    bugService.createBug(bug);
  }

  public void updateBugStatus(Bug bug, String status) {
    String oldStatus = bug.getStatus();
    bug.setStatus(status);
    bugService.updateBug(bug);

    // Notify Tester if status changes to Completed
    if ("Completed".equalsIgnoreCase(status) && !"Completed".equalsIgnoreCase(oldStatus)) {
      String body = "Bug ID: " + bug.getId() + " (" + bug.getTitle()
          + ") has been marked as Completed by the Developer.\nPlease verify.";
      emailService.sendEmail("Tester", "Bug marked as Completed - " + bug.getTitle(), body);
    }
  }

  public void updateBug(Bug bug) {
    // We need to check if assignee changed. This is tricky without the old object.
    // However, usually updateBug is called for full updates.
    // For this simple system, let's assume if assigneeId > 0, we notify.
    // Ideally we'd fetch the old bug to compare, but that might be expensive for
    // every update.
    // Let's do it right: fetch old bug to compare assignee.

    // Find old bug
    List<Bug> allBugs = bugService.getAllBugs();
    Bug oldBug = null;
    for (Bug b : allBugs) {
      if (b.getId() == bug.getId()) {
        oldBug = b;
        break;
      }
    }

    bugService.updateBug(bug);

    if (oldBug != null) {
      // Check for Assignment
      if (bug.getAssigneeId() != 0 && bug.getAssigneeId() != oldBug.getAssigneeId()) {
        String body = "You have been assigned a new bug.\nID: " + bug.getId() + "\nTitle: " + bug.getTitle();
        emailService.sendEmail("Developer (ID: " + bug.getAssigneeId() + ")", "New Bug Assigned - " + bug.getTitle(),
            body);
      }

      // Also check status change here just in case this method is used instead of
      // updateBugStatus
      if ("Completed".equalsIgnoreCase(bug.getStatus()) && !"Completed".equalsIgnoreCase(oldBug.getStatus())) {
        String body = "Bug ID: " + bug.getId() + " (" + bug.getTitle()
            + ") has been marked as Completed.\nPlease verify.";
        emailService.sendEmail("Tester", "Bug marked as Completed - " + bug.getTitle(), body);
      }
    }
  }

  public void addComment(Bug bug, String comment) {
    // Appending comment to description as a simple persistence mechanism
    String currentDesc = bug.getDescription();
    String timestamp = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    String newDesc = currentDesc + "\n\n[" + timestamp + "] Comment: " + comment;
    bug.setDescription(newDesc);
    bugService.updateBug(bug);
  }
}
