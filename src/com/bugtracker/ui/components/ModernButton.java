package com.bugtracker.ui.components;

import com.bugtracker.util.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {

  public ModernButton(String text) {
    super(text);
    init();
  }

  private void init() {
    setFont(StyleUtils.FONT_BOLD);
    setBackground(StyleUtils.COLOR_BUTTON_BG);
    setForeground(StyleUtils.COLOR_FOREGROUND);
    setFocusPainted(false);
    setBorder(BorderFactory.createLineBorder(StyleUtils.COLOR_BUTTON_BORDER));
    setContentAreaFilled(true);
    setOpaque(true);
    setCursor(new Cursor(Cursor.HAND_CURSOR));

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        setBackground(StyleUtils.COLOR_BUTTON_BG.brighter());
      }

      @Override
      public void mouseExited(MouseEvent e) {
        setBackground(StyleUtils.COLOR_BUTTON_BG);
      }
    });
  }
}
