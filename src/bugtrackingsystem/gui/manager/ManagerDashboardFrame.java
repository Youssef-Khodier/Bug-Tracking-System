/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bugtrackingsystem.gui.manager;

import bugtrackingsystem.gui.BaseDashboardFrame;
import bugtrackingsystem.util.StyleUtils;

/**
 *
 * @author Y416
 */
public class ManagerDashboardFrame extends BaseDashboardFrame {

  private bugtrackingsystem.controller.BugController bugController;
  private javax.swing.table.DefaultTableModel tableModel;

  // Components
  private javax.swing.JTable jTable1;
  private javax.swing.JTextPane jTextPane3; // Pending
  private javax.swing.JTextPane jTextPane4; // Total
  private javax.swing.JTextPane jTextPane5; // Closed

  public ManagerDashboardFrame() {
    super("Project Manager Dashboard"); // Calls initBaseUI -> createContentPanel

    // Init Controller
    bugController = new bugtrackingsystem.controller.BugController();

    refreshDashboard();
  }

  @Override
  protected void addHeaderButtons(javax.swing.JPanel headerActions) {
    // No extra buttons in Manager header, just Logout provided by base
  }

  @Override
  protected javax.swing.JPanel createContentPanel() {
    javax.swing.JPanel contentPanel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 20));
    contentPanel.setBackground(StyleUtils.BACKGROUND_COLOR);
    contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 20, 20));

    // Stats Panel
    javax.swing.JPanel statsPanel = new javax.swing.JPanel(new java.awt.GridLayout(1, 3, 20, 0));
    statsPanel.setBackground(StyleUtils.BACKGROUND_COLOR);

    jTextPane3 = new javax.swing.JTextPane();
    jTextPane4 = new javax.swing.JTextPane();
    jTextPane5 = new javax.swing.JTextPane();

    statsPanel.add(createStatPanel("Total Bugs", jTextPane4));
    statsPanel.add(createStatPanel("Pending Bugs", jTextPane3));
    statsPanel.add(createStatPanel("Closed Bugs", jTextPane5));

    contentPanel.add(statsPanel, java.awt.BorderLayout.NORTH);

    // Table
    jTable1 = new javax.swing.JTable();
    StyleUtils.styleTable(jTable1);

    String[] columns = { "ID", "Title", "Status", "Priority", "Reporter", "Assignee" };
    tableModel = new javax.swing.table.DefaultTableModel(columns, 0);
    jTable1.setModel(tableModel);

    contentPanel.add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);

    return contentPanel;
  }

  // Helper for Stats
  private javax.swing.JPanel createStatPanel(String title, javax.swing.JTextPane textPane) {
    javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
    panel.setBackground(StyleUtils.BACKGROUND_COLOR);
    panel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY));

    javax.swing.JLabel lbl = new javax.swing.JLabel(title);
    lbl.setForeground(StyleUtils.TEXT_COLOR);
    lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

    textPane.setEditable(false);

    panel.add(lbl, java.awt.BorderLayout.NORTH);
    panel.add(new javax.swing.JScrollPane(textPane), java.awt.BorderLayout.CENTER);
    return panel;
  }

  private void refreshDashboard() {
    if (bugController == null)
      return;
    java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();

    int total = bugs.size();
    int closed = 0;
    int open = 0;

    tableModel.setRowCount(0);
    for (bugtrackingsystem.model.Bug b : bugs) {
      if ("Closed".equalsIgnoreCase(b.getStatus())) {
        closed++;
      } else {
        open++;
      }
      tableModel.addRow(new Object[] { b.getId(), b.getTitle(), b.getStatus(), b.getPriority(), b.getReporterId(),
          b.getAssigneeId() });
    }

    jTextPane4.setText(String.valueOf(total));
    jTextPane3.setText(String.valueOf(open));
    jTextPane5.setText(String.valueOf(closed));
  }
}
