/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bugtrackingsystem.gui.admin;

/**
 *
 * @author Y416
 */
public class AdminDashboardFrame extends javax.swing.JFrame {

  /**
   * Creates new form AdminDashboardFrame
   */
  private bugtrackingsystem.controller.UserController userController;
  private bugtrackingsystem.controller.BugController bugController;
  private javax.swing.JTable userTable;
  private javax.swing.table.DefaultTableModel userTableModel;
  private javax.swing.JTable bugTable;
  private javax.swing.table.DefaultTableModel bugTableModel;
  private javax.swing.JPanel cardPanel;
  private java.awt.CardLayout cardLayout;

  /**
   * Creates new form AdminDashboardFrame
   */
  public AdminDashboardFrame() {
    initComponents();
    userController = new bugtrackingsystem.controller.UserController();
    bugController = new bugtrackingsystem.controller.BugController();
    bugtrackingsystem.util.StyleUtils.applySystemStyles();
    initCustomUI();
    refreshUserTable();
    refreshBugTable();
  }

  private void initCustomUI() {
    setTitle("Admin Dashboard");
    getContentPane().setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    getContentPane().setLayout(new java.awt.BorderLayout());

    // --- Header Panel (Title + Navigation) ---
    javax.swing.JPanel headerPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
    headerPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));

    // Title Label (Left)
    javax.swing.JLabel titleLabel = new javax.swing.JLabel("Admin Dashboard");
    bugtrackingsystem.util.StyleUtils.styleHeader(titleLabel);
    titleLabel.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);
    headerPanel.add(titleLabel, java.awt.BorderLayout.WEST);

    // Navigation Buttons (Right)
    javax.swing.JPanel navButtonsPanel = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
    navButtonsPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    javax.swing.JButton showUsersBtn = new javax.swing.JButton("Manage Users");
    javax.swing.JButton showBugsBtn = new javax.swing.JButton("View Bugs");
    javax.swing.JButton logoutBtn = new javax.swing.JButton("Logout");

    bugtrackingsystem.util.StyleUtils.styleButton(showUsersBtn);
    bugtrackingsystem.util.StyleUtils.styleButton(showBugsBtn);
    bugtrackingsystem.util.StyleUtils.styleButton(logoutBtn);

    showUsersBtn.addActionListener(e -> cardLayout.show(cardPanel, "users"));
    showBugsBtn.addActionListener(e -> {
      refreshBugTable();
      cardLayout.show(cardPanel, "bugs");
    });
    logoutBtn.addActionListener(e -> {
      dispose();
      new bugtrackingsystem.gui.auth.LoginFrame().setVisible(true);
    });

    navButtonsPanel.add(showUsersBtn);
    navButtonsPanel.add(showBugsBtn);
    navButtonsPanel.add(logoutBtn);
    headerPanel.add(navButtonsPanel, java.awt.BorderLayout.EAST);

    add(headerPanel, java.awt.BorderLayout.NORTH);

    // --- Main Content (Card Layout) ---
    cardLayout = new java.awt.CardLayout();
    cardPanel = new javax.swing.JPanel(cardLayout);
    cardPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    // Add margin around the content
    cardPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 20, 20));

    // 1. Users Panel
    javax.swing.JPanel usersPanel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10)); // Gap between Toolbar and
                                                                                              // Table
    usersPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    // User Actions Toolbar
    javax.swing.JPanel userActionsPanel = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
    userActionsPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);
    // Add slightly more distinct look or spacing
    userActionsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));

    javax.swing.JButton addUserBtn = new javax.swing.JButton("Add User");
    javax.swing.JButton editUserBtn = new javax.swing.JButton("Edit User");
    javax.swing.JButton deleteUserBtn = new javax.swing.JButton("Delete User");

    // Add gaps manually or use FlowLayout gaps. Let's add rigid areas if needed, or
    // just set FlowLayout gap above.
    // Actually, distinct buttons need gaps.
    userActionsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

    bugtrackingsystem.util.StyleUtils.styleButton(addUserBtn);
    bugtrackingsystem.util.StyleUtils.styleButton(editUserBtn);
    bugtrackingsystem.util.StyleUtils.styleButton(deleteUserBtn);

    addUserBtn.addActionListener(e -> showUserDialog(null));
    editUserBtn.addActionListener(e -> editSelectedUser());
    deleteUserBtn.addActionListener(e -> deleteSelectedUser());

    userActionsPanel.add(addUserBtn);
    userActionsPanel.add(editUserBtn);
    userActionsPanel.add(deleteUserBtn);

    String[] userCols = { "ID", "Username", "Role" };
    userTableModel = new javax.swing.table.DefaultTableModel(userCols, 0);
    userTable = new javax.swing.JTable(userTableModel);
    bugtrackingsystem.util.StyleUtils.styleTable(userTable);

    usersPanel.add(userActionsPanel, java.awt.BorderLayout.NORTH);
    usersPanel.add(new javax.swing.JScrollPane(userTable), java.awt.BorderLayout.CENTER);

    // 2. Bugs Panel
    javax.swing.JPanel bugsPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
    bugsPanel.setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    // Add a title or just the table? Let's keep it simple for now, maybe a label
    // "All System Bugs"
    javax.swing.JLabel bugsTitle = new javax.swing.JLabel("System Bugs Registry");
    bugtrackingsystem.util.StyleUtils.styleLabel(bugsTitle);
    bugsTitle.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);
    bugsTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
    bugsPanel.add(bugsTitle, java.awt.BorderLayout.NORTH);

    String[] bugCols = { "ID", "Title", "Status", "Priority", "Reporter", "Assignee", "Project" };
    bugTableModel = new javax.swing.table.DefaultTableModel(bugCols, 0);
    bugTable = new javax.swing.JTable(bugTableModel);
    bugtrackingsystem.util.StyleUtils.styleTable(bugTable);

    bugsPanel.add(new javax.swing.JScrollPane(bugTable), java.awt.BorderLayout.CENTER);

    // Add cards
    cardPanel.add(usersPanel, "users");
    cardPanel.add(bugsPanel, "bugs");

    add(cardPanel, java.awt.BorderLayout.CENTER);
  }

  private void refreshUserTable() {
    userTableModel.setRowCount(0);
    java.util.List<bugtrackingsystem.model.User> users = userController.getAllUsers();
    for (bugtrackingsystem.model.User u : users) {
      userTableModel.addRow(new Object[] { u.getId(), u.getUsername(), u.getRole() });
    }
  }

  private void refreshBugTable() {
    bugTableModel.setRowCount(0);
    java.util.List<bugtrackingsystem.model.Bug> bugs = bugController.getAllBugs();
    for (bugtrackingsystem.model.Bug b : bugs) {
      bugTableModel.addRow(new Object[] {
          b.getId(), b.getTitle(), b.getStatus(), b.getPriority(),
          b.getReporterId(), b.getAssigneeId(), b.getProjectId()
      });
    }
  }

  private void editSelectedUser() {
    int row = userTable.getSelectedRow();
    if (row == -1) {
      javax.swing.JOptionPane.showMessageDialog(this, "Please select a user to edit.");
      return;
    }
    int id = (int) userTableModel.getValueAt(row, 0);
    // Fetch the actual user object from controller/service if possible or iterate
    // list.
    java.util.Optional<bugtrackingsystem.model.User> userOpt = userController.getAllUsers().stream()
        .filter(u -> u.getId() == id).findFirst();

    if (userOpt.isPresent()) {
      showUserDialog(userOpt.get());
    }
  }

  private void deleteSelectedUser() {
    int row = userTable.getSelectedRow();
    if (row == -1) {
      javax.swing.JOptionPane.showMessageDialog(this, "Please select a user to delete.");
      return;
    }
    int id = (int) userTableModel.getValueAt(row, 0);
    int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Are you sure you want to delete user ID " + id + "?", "Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
      userController.deleteUser(id);
      refreshUserTable();
    }
  }

  private void showUserDialog(bugtrackingsystem.model.User existingUser) {
    javax.swing.JTextField usernameField = new javax.swing.JTextField();
    javax.swing.JPasswordField passwordField = new javax.swing.JPasswordField();
    String[] roles = { "Admin", "Manager", "Developer", "Tester" };
    javax.swing.JComboBox<String> roleBox = new javax.swing.JComboBox<>(roles);

    if (existingUser != null) {
      usernameField.setText(existingUser.getUsername());
      passwordField.setText(existingUser.getPassword());
      roleBox.setSelectedItem(existingUser.getRole());
    }

    Object[] message = {
        "Username:", usernameField,
        "Password:", passwordField,
        "Role:", roleBox
    };

    String title = (existingUser == null) ? "Add New User" : "Edit User";
    int option = javax.swing.JOptionPane.showConfirmDialog(this, message, title,
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (option == javax.swing.JOptionPane.OK_OPTION) {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());
      String role = (String) roleBox.getSelectedItem();
      if (!username.isEmpty() && !password.isEmpty()) {
        if (existingUser == null) {
          userController.addUser(username, password, role);
        } else {
          userController.updateUser(existingUser.getId(), username, password, role);
        }
        refreshUserTable();
      } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Fields cannot be empty");
      }
    }
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

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the System look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    bugtrackingsystem.util.StyleUtils.applySystemStyles();
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new AdminDashboardFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
