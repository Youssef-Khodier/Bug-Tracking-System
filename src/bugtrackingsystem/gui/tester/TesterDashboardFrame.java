/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bugtrackingsystem.gui.tester;

/**
 *
 * @author Y416
 */
public class TesterDashboardFrame extends javax.swing.JFrame {

  /**
   * Creates new form TesterDashboardFrame
   */
  private bugtrackingsystem.controller.BugController bugController;
  private javax.swing.table.DefaultTableModel tableModel;

  /**
   * Creates new form TesterDashboardFrame
   */
  public TesterDashboardFrame() {
    initComponents();
    bugController = new bugtrackingsystem.controller.BugController();
    bugtrackingsystem.util.StyleUtils.applySystemStyles();
    initCustomUI();
    refreshBugTable();
  }

  private void initCustomUI() {
    setTitle("Tester Dashboard - Bug Management");
    getContentPane().removeAll();
    getContentPane().setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    getContentPane().setLayout(new java.awt.BorderLayout());

    // --- Header ---
    javax.swing.JPanel headerPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
    headerPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));

    javax.swing.JLabel titleLabel = new javax.swing.JLabel("Tester Dashboard");
    bugtrackingsystem.util.StyleUtils.styleHeader(titleLabel);
    titleLabel.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);
    headerPanel.add(titleLabel, java.awt.BorderLayout.WEST);

    javax.swing.JButton logoutBtn = new javax.swing.JButton("Logout"); // Use local since jButton3 is used for "Logout"
                                                                       // in original, but let's just make a new one or
                                                                       // reuse.
    // Original jButton3 was "Logout". I'll reuse it if I can or just create new.
    // Creating new for consistency with other refactors.
    bugtrackingsystem.util.StyleUtils.styleButton(logoutBtn);
    logoutBtn.addActionListener(e -> {
      dispose();
      new bugtrackingsystem.gui.auth.LoginFrame().setVisible(true);
    });
    headerPanel.add(logoutBtn, java.awt.BorderLayout.EAST);

    add(headerPanel, java.awt.BorderLayout.NORTH);

    // --- Content ---
    javax.swing.JPanel contentPanel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 15));
    contentPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 20, 20));

    // Toolbar (Create + Refresh + Filter)
    javax.swing.JPanel toolbar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
    toolbar.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    bugtrackingsystem.util.StyleUtils.styleButton(jButton1); // Create
    bugtrackingsystem.util.StyleUtils.styleButton(jButton2); // Refresh

    javax.swing.JLabel filterLbl = new javax.swing.JLabel("Filter:");
    filterLbl.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);

    toolbar.add(jButton1);
    toolbar.add(jButton2);
    toolbar.add(filterLbl);
    toolbar.add(jComboBox1);

    contentPanel.add(toolbar, java.awt.BorderLayout.NORTH);

    // Table
    bugtrackingsystem.util.StyleUtils.styleTable(jTable1);
    contentPanel.add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);

    // Bottom Actions (Edit, Assign)
    javax.swing.JPanel bottomPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
    bottomPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    bugtrackingsystem.util.StyleUtils.styleButton(jButton4); // Edit
    bugtrackingsystem.util.StyleUtils.styleButton(jButton5); // Assign

    bottomPanel.add(jButton4);
    bottomPanel.add(jButton5);

    contentPanel.add(bottomPanel, java.awt.BorderLayout.SOUTH);

    add(contentPanel, java.awt.BorderLayout.CENTER);

    // Data Init
    String[] columns = { "ID", "Title", "Status", "Priority", "Assignee", "Project" };
    tableModel = new javax.swing.table.DefaultTableModel(columns, 0);
    jTable1.setModel(tableModel);

    // ComboBox
    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Open", "Closed" }));

    // Actions
    jButton1.addActionListener(e -> showCreateBugDialog());
    jButton2.addActionListener(e -> refreshBugTable());
    // jButton3 is Logout in generated code, but we made a new one.
    // We can ignore jButton3 or reuse logic.
    jButton4.addActionListener(e -> showEditBugDialog());
    jButton5.addActionListener(e -> showAssignDialog());
    jComboBox1.addActionListener(e -> refreshBugTable());
  }

  private void refreshBugTable() {
    tableModel.setRowCount(0);
    java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
    String filter = (String) jComboBox1.getSelectedItem();

    for (bugtrackingsystem.model.Bug b : bugs) {
      if ("All".equals(filter) || b.getStatus().equalsIgnoreCase(filter)) {
        tableModel.addRow(new Object[] { b.getId(), b.getTitle(), b.getStatus(), b.getPriority(),
            b.getAssigneeId(), b.getProjectId() });
      }
    }
  }

  private void showCreateBugDialog() {
    javax.swing.JTextField titleField = new javax.swing.JTextField();
    javax.swing.JTextArea descField = new javax.swing.JTextArea(5, 20);
    String[] priorities = { "Low", "Medium", "High" };
    javax.swing.JComboBox<String> priorityBox = new javax.swing.JComboBox<>(priorities);

    // New Fields
    javax.swing.JComboBox<String> typeBox = new javax.swing.JComboBox<>(
        new String[] { "Bug", "Error", "Feature Request" });
    javax.swing.JComboBox<String> levelBox = new javax.swing.JComboBox<>(new String[] { "Critical", "Major", "Minor" });
    javax.swing.JComboBox<String> statusBox = new javax.swing.JComboBox<>(
        new String[] { "Open", "In Progress", "Closed" });
    javax.swing.JTextField dateField = new javax.swing.JTextField(java.time.LocalDate.now().toString());

    javax.swing.JButton attachBtn = new javax.swing.JButton("Attach Screenshot");
    javax.swing.JLabel fileLabel = new javax.swing.JLabel("No file selected");
    final java.io.File[] selectedFileHolder = new java.io.File[1];

    attachBtn.addActionListener(e -> {
      javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
      if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        selectedFileHolder[0] = fc.getSelectedFile();
        fileLabel.setText(selectedFileHolder[0].getName());
      }
    });

    // Fetch Projects using FileRepository (Direct access for simplicity)
    bugtrackingsystem.service.FileRepository fileRepo = new bugtrackingsystem.service.FileRepository();
    java.util.List<bugtrackingsystem.model.Project> projects = fileRepo.loadProjects();
    java.util.List<String> projectOptions = new java.util.ArrayList<>();

    for (bugtrackingsystem.model.Project p : projects) {
      projectOptions.add(p.getId() + " - " + p.getName());
    }

    javax.swing.JComboBox<String> projectBox = new javax.swing.JComboBox<>(projectOptions.toArray(new String[0]));

    Object[] message = {
        "Title:", titleField,
        "Description:", new javax.swing.JScrollPane(descField),
        "Type:", typeBox,
        "Priority:", priorityBox,
        "Level:", levelBox,
        "Status:", statusBox,
        "Date:", dateField,
        "Project:", projectBox,
        "Screenshot:", attachBtn, fileLabel
    };

    int option = javax.swing.JOptionPane.showConfirmDialog(this, message, "Report New Bug",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);
    if (option == javax.swing.JOptionPane.OK_OPTION) {
      try {
        String title = titleField.getText();
        String desc = descField.getText();
        String priority = (String) priorityBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();
        String level = (String) levelBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        String date = dateField.getText();

        java.io.File screenshotFile = selectedFileHolder[0];

        String selectedProject = (String) projectBox.getSelectedItem();
        int projectId = -1;
        if (selectedProject != null) {
          projectId = Integer.parseInt(selectedProject.split(" - ")[0]);
        } else {
          // Fallback or error if no project selected
          javax.swing.JOptionPane.showMessageDialog(this, "Please select a project.");
          return;
        }

        // Assuming current user is reporter (need to pass user context, for now using
        // 1)
        bugController.createBug(title, desc, priority, 1, projectId, type, level, date, status, screenshotFile);
        refreshBugTable();
      } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error parsing Project ID");
      }
    }
  }

  private void showEditBugDialog() {
    int row = jTable1.getSelectedRow();
    if (row == -1) {
      javax.swing.JOptionPane.showMessageDialog(this, "Select a bug to edit");
      return;
    }

    int id = (int) tableModel.getValueAt(row, 0);
    // Find the bug object
    java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
    bugtrackingsystem.model.Bug selectedBug = null;
    for (bugtrackingsystem.model.Bug b : bugs) {
      if (b.getId() == id) {
        selectedBug = b;
        break;
      }
    }

    if (selectedBug == null)
      return;

    javax.swing.JTextField titleField = new javax.swing.JTextField(selectedBug.getTitle());
    javax.swing.JTextArea descField = new javax.swing.JTextArea(selectedBug.getDescription(), 5, 20);
    String[] priorities = { "Low", "Medium", "High" };
    javax.swing.JComboBox<String> priorityBox = new javax.swing.JComboBox<>(priorities);
    priorityBox.setSelectedItem(selectedBug.getPriority());
    String[] statuses = { "Open", "Closed" }; // Testers usually just Open/Close or verify? Or maybe full status edit.
    javax.swing.JComboBox<String> statusBox = new javax.swing.JComboBox<>(statuses);
    statusBox.setSelectedItem(selectedBug.getStatus());

    Object[] message = {
        "Title:", titleField,
        "Description:", new javax.swing.JScrollPane(descField),
        "Priority:", priorityBox,
        "Status:", statusBox
    };

    int option = javax.swing.JOptionPane.showConfirmDialog(this, message, "Edit Bug",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);
    if (option == javax.swing.JOptionPane.OK_OPTION) {
      selectedBug.setTitle(titleField.getText());
      selectedBug.setDescription(descField.getText());
      selectedBug.setPriority((String) priorityBox.getSelectedItem());
      selectedBug.setStatus((String) statusBox.getSelectedItem());

      bugController.updateBug(selectedBug);
      refreshBugTable();
    }
  }

  private void showAssignDialog() {
    int row = jTable1.getSelectedRow();
    if (row == -1) {
      javax.swing.JOptionPane.showMessageDialog(this, "Select a bug to assign");
      return;
    }

    int id = (int) tableModel.getValueAt(row, 0);

    // Fetch Developers
    bugtrackingsystem.controller.UserController userController = new bugtrackingsystem.controller.UserController();
    java.util.List<bugtrackingsystem.model.User> allUsers = userController.getAllUsers();
    java.util.List<String> devOptions = new java.util.ArrayList<>();

    for (bugtrackingsystem.model.User u : allUsers) {
      if ("Developer".equalsIgnoreCase(u.getRole())) {
        devOptions.add(u.getId() + " - " + u.getUsername());
      }
    }

    if (devOptions.isEmpty()) {
      javax.swing.JOptionPane.showMessageDialog(this, "No developers found in the system.");
      return;
    }

    javax.swing.JComboBox<String> devBox = new javax.swing.JComboBox<>(devOptions.toArray(new String[0]));

    int result = javax.swing.JOptionPane.showConfirmDialog(this, devBox, "Select Developer",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (result == javax.swing.JOptionPane.OK_OPTION) {
      String selected = (String) devBox.getSelectedItem();
      if (selected != null) {
        try {
          // Extract ID from "ID - Name"
          int devId = Integer.parseInt(selected.split(" - ")[0]);

          // Find bug and update
          java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
          for (bugtrackingsystem.model.Bug b : bugs) {
            if (b.getId() == id) {
              b.setAssigneeId(devId);
              bugController.updateBug(b);
              break;
            }
          }
          refreshBugTable();
        } catch (Exception e) {
          javax.swing.JOptionPane.showMessageDialog(this, "Error assigning developer: " + e.getMessage());
        }
      }
    }
  }

  private void logout() {
    new bugtrackingsystem.gui.auth.LoginFrame().setVisible(true);
    dispose();
  }

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    refreshBugTable();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    jComboBox1 = new javax.swing.JComboBox<>();
    jLabel2 = new javax.swing.JLabel();
    jButton3 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jButton1.setText("Create Bug");

    jLabel1.setText("Tester Dashboard");

    jButton2.setText("Refresh");

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }));
    jScrollPane1.setViewportView(jTable1);

    jButton4.setText("Edit Bug");

    jButton5.setText("Assign to developer");

    jComboBox1
        .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1ActionPerformed(evt);
      }
    });

    jLabel2.setText("Filter");

    jButton3.setText("Logout");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(49, 49, 49)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(TesterDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(TesterDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(TesterDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(TesterDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    }
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TesterDashboardFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JButton jButton5;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;
  // End of variables declaration//GEN-END:variables
}
