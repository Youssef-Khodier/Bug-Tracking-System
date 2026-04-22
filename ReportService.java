package com.bugtracker.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import com.bugtracker.entity.Bug;
import com.bugtracker.repository.BugRepository;

public class ReportService {

    private BugRepository bugRepository;

    public ReportService() {
        this.bugRepository = new BugRepository();
    }

    public Map<String, Integer> getBugCounts() {
        Map<String, Integer> stats = new HashMap<>();

        List<Bug> allBugs = bugRepository.findAll();

        int openCount = 0;
        int inProgressCount = 0;
        int completedCount = 0;

        for (Bug bug : allBugs) {
            if (bug.getStatus() == Bug.Status.OPEN) {
                openCount++;
            } else if (bug.getStatus() == Bug.Status.IN_PROGRESS) {
                inProgressCount++;
            } else if (bug.getStatus() == Bug.Status.COMPLETED) {
                completedCount++;
            }
        }

        stats.put("Open", openCount);
        stats.put("In Progress", inProgressCount);
        stats.put("Completed", completedCount);

        return stats;
    }

    public Map<String, Integer> getDeveloperPerformance() {
        Map<String, Integer> performance = new HashMap<>();

        List<Bug> allBugs = bugRepository.findAll();

        for (Bug bug : allBugs) {
            if (bug.getStatus() == Bug.Status.COMPLETED) {
                String developerName = "Developer ID: " + bug.getAssigneeId();

                if (performance.containsKey(developerName)) {
                    performance.put(
                        developerName,
                        performance.get(developerName) + 1
                    );
                } else {
                    performance.put(developerName, 1);
                }
            }
        }

        return performance;
    }

    public void generateTextReport(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {

            Map<String, Integer> stats = getBugCounts();

            writer.write("Open Bugs: " + stats.get("Open") + "\n");
            writer.write("In Progress Bugs: " + stats.get("In Progress") + "\n");
            writer.write("Completed Bugs: " + stats.get("Completed") + "\n\n");

            Map<String, Integer> performance = getDeveloperPerformance();

            writer.write("Developer Performance:\n");

            for (Map.Entry<String, Integer> entry : performance.entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
