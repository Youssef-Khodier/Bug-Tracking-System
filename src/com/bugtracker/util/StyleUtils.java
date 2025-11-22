package com.bugtracker.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class StyleUtils {

  public static final Color COLOR_BACKGROUND = new Color(0x2E2E2E);
  public static final Color COLOR_FOREGROUND = new Color(0xE0E0E0);
  public static final Color COLOR_BUTTON_BG = new Color(0x3C3F41);
  public static final Color COLOR_BUTTON_BORDER = new Color(0x5E5E5E);
  public static final Color COLOR_TEXT_FIELD_BG = new Color(0x252526);
  public static final Color COLOR_ACCENT = new Color(0x3A9AD9); // Example accent

  public static final Font FONT_MAIN = new Font("Segoe UI", Font.PLAIN, 14);
  public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);

  public static void applyDarkTheme() {
    // Set common properties
    UIManager.put("Panel.background", new ColorUIResource(COLOR_BACKGROUND));
    UIManager.put("Frame.background", new ColorUIResource(COLOR_BACKGROUND));
    UIManager.put("Label.background", new ColorUIResource(COLOR_BACKGROUND));
    UIManager.put("Label.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("Label.font", FONT_MAIN);

    // Buttons
    UIManager.put("Button.background", new ColorUIResource(COLOR_BUTTON_BG));
    UIManager.put("Button.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("Button.border", new LineBorder(COLOR_BUTTON_BORDER));
    UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0))); // Transparent focus
    UIManager.put("Button.font", FONT_BOLD);

    // TextFields
    UIManager.put("TextField.background", new ColorUIResource(COLOR_TEXT_FIELD_BG));
    UIManager.put("TextField.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("TextField.caretForeground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("TextField.border", new LineBorder(COLOR_BUTTON_BORDER));
    UIManager.put("TextField.font", FONT_MAIN);

    // PasswordFields
    UIManager.put("PasswordField.background", new ColorUIResource(COLOR_TEXT_FIELD_BG));
    UIManager.put("PasswordField.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("PasswordField.caretForeground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("PasswordField.border", new LineBorder(COLOR_BUTTON_BORDER));

    // Tables
    UIManager.put("Table.background", new ColorUIResource(COLOR_TEXT_FIELD_BG));
    UIManager.put("Table.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("Table.selectionBackground", new ColorUIResource(COLOR_ACCENT));
    UIManager.put("Table.selectionForeground", new ColorUIResource(Color.WHITE));
    UIManager.put("Table.gridColor", new ColorUIResource(COLOR_BUTTON_BORDER));
    UIManager.put("Table.font", FONT_MAIN);
    UIManager.put("TableHeader.background", new ColorUIResource(COLOR_BUTTON_BG));
    UIManager.put("TableHeader.foreground", new ColorUIResource(COLOR_FOREGROUND));
    UIManager.put("TableHeader.font", FONT_BOLD);

    // ScrollPane
    UIManager.put("ScrollPane.background", new ColorUIResource(COLOR_BACKGROUND));
    UIManager.put("Viewport.background", new ColorUIResource(COLOR_BACKGROUND));

    // OptionPane
    UIManager.put("OptionPane.background", new ColorUIResource(COLOR_BACKGROUND));
    UIManager.put("OptionPane.messageForeground", new ColorUIResource(COLOR_FOREGROUND));
  }
}
