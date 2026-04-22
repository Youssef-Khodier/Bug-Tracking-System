/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bugtrackingsystem.gui.developer;

import bugtrackingsystem.gui.BaseDashboardFrame;
import bugtrackingsystem.util.StyleUtils;

/**
 *
 * @author Y416
 */
public class DeveloperDashboardFrame extends BaseDashboardFrame {

  private bugtrackingsystem.controller.BugController bugController;
  private javax.swing.table.DefaultTableModel tableModel;

  // Components
  private javax.swing.JTable jTable1;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JTextField jTextField1;

  public DeveloperDashboardFrame() {
    super("Developer Dashboard"); // Calls initBaseUI -> createContentPanel

    // Init Controller
    bugController = new bugtrackingsystem.controller.BugController();

    refreshBugTable();
  }

  @Override
  protected void addHeaderButtons(javax.swing.JPanel headerActions) {
    javax.swing.JButton refreshBtn = new javax.swing.JButton("Refresh");
    StyleUtils.styleButton(refreshBtn);
    refreshBtn.addActionListener(e -> refreshBugTable());
    headerActions.add(refreshBtn);
  }

  @Override
  protected javax.swing.JPanel createContentPanel() {
    javax.swing.JPanel contentPanel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 15));
    contentPanel.setBackground(StyleUtils.BACKGROUND_COLOR);
    contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 20, 20));

    // Table
    jTable1 = new javax.swing.JTable();
    StyleUtils.styleTable(jTable1);

    String[] columns = { "ID", "Title", "Status", "Priority", "Project" };
    tableModel = new javax.swing.table.DefaultTableModel(columns, 0);
    jTable1.setModel(tableModel);

    contentPanel.add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);

    // Bottom Action Panel
    javax.swing.JPanel bottomPanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1, 0, 15));
    bottomPanel.setBackground(StyleUtils.BACKGROUND_COLOR);

    // 1. Status Update Row
    javax.swing.JPanel statusPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    statusPanel.setBackground(StyleUtils.BACKGROUND_COLOR);

    javax.swing.JLabel statusLbl = new javax.swing.JLabel("Status:");
    StyleUtils.styleLabel(statusLbl);
    statusLbl.setForeground(StyleUtils.TEXT_COLOR);

    jComboBox1 = new javax.swing.JComboBox<>(new String[] { "Open", "In Progress", "Completed" });
    jComboBox1.setPreferredSize(new java.awt.Dimension(150, 30));

    javax.swing.JButton updateBtn = new javax.swing.JButton("Update Status");
    StyleUtils.styleButton(updateBtn);
    updateBtn.addActionListener(e -> updateStatus());

    statusPanel.add(statusLbl);
    statusPanel.add(jComboBox1);
    statusPanel.add(updateBtn);

    // 2. Comment Row
    javax.swing.JPanel commentPanel = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
    commentPanel.setBackground(StyleUtils.BACKGROUND_COLOR);

    jTextField1 = new javax.swing.JTextField();
    jTextField1.setPreferredSize(new java.awt.Dimension(300, 30));

    javax.swing.JButton commentBtn = new javax.swing.JButton("Comment");
    StyleUtils.styleButton(commentBtn);
    commentBtn.addActionListener(e -> addComment());

    commentPanel.add(jTextField1, java.awt.BorderLayout.CENTER);
    commentPanel.add(commentBtn, java.awt.BorderLayout.EAST);

    bottomPanel.add(statusPanel);
    bottomPanel.add(commentPanel);

    contentPanel.add(bottomPanel, java.awt.BorderLayout.SOUTH);

    return contentPanel;
  }

  private void refreshBugTable() {
    if (bugController == null)
      return;
    tableModel.setRowCount(0);
    java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
    for (bugtrackingsystem.model.Bug b : bugs) {
      tableModel.addRow(new Object[] { b.getId(), b.getTitle(), b.getStatus(), b.getPriority(), b.getReporterId(),
          b.getProjectId() });
    }
  }

  private void updateStatus() {
    int row = jTable1.getSelectedRow();
    if (row != -1) {
      int id = (int) tableModel.getValueAt(row, 0);
      String status = (String) jComboBox1.getSelectedItem();

      for (bugtrackingsystem.model.Bug b : bugController.getAllBugs()) {
        if (b.getId() == id) {
          b.setStatus(status);
          bugController.updateBugStatus(b, status);
          break;
        }
      }
      refreshBugTable();
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "Select a bug to update");
    }
  }

  private void addComment() {
    String comment = jTextField1.getText();
    int row = jTable1.getSelectedRow();
    if (row == -1) {
      javax.swing.JOptionPane.showMessageDialog(this, "Select a bug to comment on");
      return;
    }
    if (!comment.isEmpty()) {
      int id = (int) tableModel.getValueAt(row, 0);
      java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
      for (bugtrackingsystem.model.Bug b : bugs) {
        if (b.getId() == id) {
          bugController.addComment(b, comment);
          break;
        }
      }
      javax.swing.JOptionPane.showMessageDialog(this, "Comment added.");
      jTextField1.setText("");
      refreshBugTable();
    }
  }
}
