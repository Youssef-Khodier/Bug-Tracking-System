package bugtrackingsystem.gui;

import javax.swing.*;
import java.awt.*;
import bugtrackingsystem.util.StyleUtils;

public abstract class BaseDashboardFrame extends JFrame {

  public BaseDashboardFrame(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Or EXIT if preferred

    setLocationRelativeTo(null);

    initBaseUI(title);
  }

  private void initBaseUI(String title) {
    getContentPane().setBackground(StyleUtils.BACKGROUND_COLOR);
    getContentPane().setLayout(new BorderLayout());

    // --- Header ---
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(StyleUtils.BACKGROUND_COLOR);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

    JLabel titleLabel = new JLabel(title);
    StyleUtils.styleHeader(titleLabel);
    titleLabel.setForeground(StyleUtils.TEXT_COLOR);
    headerPanel.add(titleLabel, BorderLayout.WEST);

    // Header Actions Wrapper
    JPanel headerActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    headerActions.setBackground(StyleUtils.BACKGROUND_COLOR);

    // Add Subclass Buttons
    addHeaderButtons(headerActions);

    // Add Logout (Standard)
    JButton logoutBtn = new JButton("Logout");
    StyleUtils.styleButton(logoutBtn);
    logoutBtn.addActionListener(e -> {
      dispose();
      new bugtrackingsystem.gui.auth.LoginFrame().setVisible(true);
    });
    headerActions.add(logoutBtn);

    headerPanel.add(headerActions, BorderLayout.EAST);
    add(headerPanel, BorderLayout.NORTH);

    // --- Content ---
    JPanel content = createContentPanel();
    if (content != null) {
      add(content, BorderLayout.CENTER);
    }
  }

  /**
   * Subclasses implement this to provide their specific UI content.
   */
  protected abstract JPanel createContentPanel();

  /**
   * Subclasses override this to add extra buttons to the header (e.g. Refresh).
   */
  protected abstract void addHeaderButtons(JPanel headerActions);
}
