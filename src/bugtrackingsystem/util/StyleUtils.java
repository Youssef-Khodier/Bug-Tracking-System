package bugtrackingsystem.util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class StyleUtils {

  public static final Color PRIMARY_COLOR = new Color(45, 45, 45); // Dark Gray
  public static final Color ACCENT_COLOR = new Color(0, 120, 215); // Blue
  public static final Color TEXT_COLOR = new Color(240, 240, 240); // White
  public static final Color BACKGROUND_COLOR = new Color(60, 63, 65); // Lighter Dark Gray

  public static void applySystemStyles() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Simple manual defaults if needed for custom components
  }

  public static void styleButton(JButton button) {
    button.setBackground(ACCENT_COLOR);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    button.setFocusPainted(false);
    button.setBorderPainted(false);
  }

  public static void styleTable(JTable table) {
    table.setRowHeight(25);
    table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
    table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    table.setGridColor(Color.LIGHT_GRAY);
  }

  public static void styleLabel(JComponent label) {
    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
  }

  public static void styleHeader(JComponent label) {
    label.setFont(new Font("Segoe UI", Font.BOLD, 18));
  }
}
