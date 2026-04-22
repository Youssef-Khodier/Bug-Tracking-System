/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bugtrackingsystem.gui.auth;

/**
 *
 * @author Y416
 */
public class LoginFrame extends javax.swing.JFrame {

  /**
   * Creates new form LoginFrame
   */
  private bugtrackingsystem.controller.LoginController controller;

  /**
   * Creates new form LoginFrame
   */
  public LoginFrame() {
    initComponents();
    controller = new bugtrackingsystem.controller.LoginController();
    bugtrackingsystem.util.StyleUtils.applySystemStyles();
    initCustomUI();
    setLocationRelativeTo(null);
  }

  private void initCustomUI() {
    // Background
    getContentPane().setBackground(bugtrackingsystem.util.StyleUtils.BACKGROUND_COLOR);

    // Title
    bugtrackingsystem.util.StyleUtils.styleHeader(jLabel3);
    jLabel3.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);

    // Labels
    bugtrackingsystem.util.StyleUtils.styleLabel(jLabel1);
    bugtrackingsystem.util.StyleUtils.styleLabel(jLabel2);
    jLabel1.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);
    jLabel2.setForeground(bugtrackingsystem.util.StyleUtils.TEXT_COLOR);

    // Button
    bugtrackingsystem.util.StyleUtils.styleButton(jButton1);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jPasswordField1 = new javax.swing.JPasswordField();
    jLabel3 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Bug Tracking System - Login");

    jLabel1.setText("Username");

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });

    jLabel2.setText("Password");

    jButton1.setText("Login");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("Login");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)
                            .addComponent(jTextField1))))
                .addContainerGap(144, Short.MAX_VALUE)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(47, 47, 47)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
    // --------------------------------
  }// GEN-LAST:event_jTextField1ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    String username = jTextField1.getText();
    String password = new String(jPasswordField1.getPassword());

    bugtrackingsystem.model.User user = controller.login(username, password);

    if (user != null) {
      javax.swing.JFrame dashboard = null;
      switch (user.getRole()) {
        case "Admin":
          dashboard = new bugtrackingsystem.gui.admin.AdminDashboardFrame();
          break;
        case "Tester":
          dashboard = new bugtrackingsystem.gui.tester.TesterDashboardFrame();
          break;
        case "Developer":
          dashboard = new bugtrackingsystem.gui.developer.DeveloperDashboardFrame();
          break;
        case "Manager":
          dashboard = new bugtrackingsystem.gui.manager.ManagerDashboardFrame();
          break;
      }

      if (dashboard != null) {
        dashboard.setVisible(true);
        this.dispose();
      } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Unknown role: " + user.getRole());
      }
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "Invalid credentials");
    }
  }// GEN-LAST:event_jButton1ActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    bugtrackingsystem.util.StyleUtils.applySystemStyles();

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new LoginFrame().setVisible(true);
      }
    });
  }

  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPasswordField jPasswordField1;
  private javax.swing.JTextField jTextField1;
}
