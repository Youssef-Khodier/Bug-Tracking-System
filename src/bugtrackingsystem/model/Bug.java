/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugtrackingsystem.model;

/**
 *
 * @author Y416
 */
public class Bug {
  private int id;
  private String title;
  private String description;
  private String status;
  private String priority;
  private int reporterId;
  private int assigneeId;
  private int projectId;
  private String type; 
  private String level; 
  private String date;
  private String screenshotPath;

  public Bug() {
  }

  public Bug(int id, String title, String description, String status, String priority, int reporterId, int assigneeId,
      int projectId, String type, String level, String date, String screenshotPath) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.reporterId = reporterId;
    this.assigneeId = assigneeId;
    this.projectId = projectId;
    this.type = type;
    this.level = level;
    this.date = date;
    this.screenshotPath = screenshotPath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public int getReporterId() {
    return reporterId;
  }

  public void setReporterId(int reporterId) {
    this.reporterId = reporterId;
  }

  public int getAssigneeId() {
    return assigneeId;
  }

  public void setAssigneeId(int assigneeId) {
    this.assigneeId = assigneeId;
  }

  public int getProjectId() {
    return projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getScreenshotPath() {
    return screenshotPath;
  }

  public void setScreenshotPath(String screenshotPath) {
    this.screenshotPath = screenshotPath;
  }

  @Override
  public String toString() {
    return id + "|" + title + "|" + description + "|" + status + "|" + priority + "|" + reporterId + "|" + assigneeId
        + "|" + projectId + "|" + (type == null ? "" : type) + "|" + (level == null ? "" : level) + "|"
        + (date == null ? "" : date) + "|" + (screenshotPath == null ? "" : screenshotPath);
  }
}
