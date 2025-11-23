package com.bugtracker.entity;

public class Bug {
    private int id;
    private String title;
    private String description;
    private String bugType;
    private String priority;
    private String bugLevel;
    private String projectName;
    private String bugDate;
    private BugStatus status;
    private int assigneeId;
    private int reporterId;
    private String screenshotPath;

    public enum BugStatus {
        OPEN,
        IN_PROGRESS,
        COMPLETED
    }

    public Bug() {
    }

    public Bug(int id, String title, String description, String bugType, String priority, 
               String bugLevel, String projectName, String bugDate, BugStatus status, 
               int assigneeId, int reporterId, String screenshotPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.bugType = bugType;
        this.priority = priority;
        this.bugLevel = bugLevel;
        this.projectName = projectName;
        this.bugDate = bugDate;
        this.status = status;
        this.assigneeId = assigneeId;
        this.reporterId = reporterId;
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

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBugDate() {
        return bugDate;
    }

    public void setBugDate(String bugDate) {
        this.bugDate = bugDate;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }
}
