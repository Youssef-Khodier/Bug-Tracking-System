/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugtrackingsystem.model;

/**
 *
 * @author Y416
 */
public class Comment {
  private int id;
  private int bugId;
  private int userId;
  private String text;
  private String timestamp;

  public Comment() {
  }

  public Comment(int id, int bugId, int userId, String text, String timestamp) {
    this.id = id;
    this.bugId = bugId;
    this.userId = userId;
    this.text = text;
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBugId() {
    return bugId;
  }

  public void setBugId(int bugId) {
    this.bugId = bugId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return id + "|" + bugId + "|" + userId + "|" + text + "|" + timestamp;
  }
}
