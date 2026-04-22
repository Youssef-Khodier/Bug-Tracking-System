package com.bugtracker.ui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import com.bugtracker.service.ReportService;
import com.bugtracker.ui.components.ModernButton;

public class PMDashboard extends JFrame {

    private ReportService reportService;

    public PMDashboard() {
        setTitle("Project Manager Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        reportService = new ReportService();

        
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Map<String, Integer> stats = reportService.getBugCounts();

        topPanel.add(createCard("Open", stats.get("Open")));
        topPanel.add(createCard("In Progress", stats.get("In Progress")));
        topPanel.add(createCard("Completed", stats.get("Completed")));

        add(topPanel, BorderLayout.NORTH);

        
        JTextArea performanceArea = new JTextArea();
        performanceArea.setEditable(false);

        Map<String, Integer> performance = reportService.getDeveloperPerformance();

        if (performance != null && !performance.isEmpty()) {
            performanceArea.append("Developer Performance Report\n\n");
            for (Map.Entry<String, Integer> entry : performance.entrySet()) {
                performanceArea.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        } else {
            performanceArea.append("No completed bugs yet.");
        }

        JScrollPane scrollPane = new JScrollPane(performanceArea);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        ModernButton exportBtn = new ModernButton("Export Report");

        exportBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                reportService.generateTextReport(
                        chooser.getSelectedFile().getAbsolutePath()
                );
                JOptionPane.showMessageDialog(this, "Report saved successfully!");
            }
        });

        bottomPanel.add(exportBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, int count) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.decode("#404040"));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);

        JLabel countLabel = new JLabel(String.valueOf(count), SwingConstants.CENTER);
        countLabel.setFont(new Font("Arial", Font.BOLD, 36));
        countLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(countLabel, BorderLayout.CENTER);

        return card;
    }
}
