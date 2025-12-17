package com.bugtracker.repository;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.Bug.BugStatus;
import java.util.*;
import java.util.stream.Collectors;

public class BugRepository extends FileRepository<Bug> {

    public BugRepository() {
        super("bugs.txt");
    }

    @Override
    public List<Bug> findAll() {
        List<String> lines = readLines();
        List<Bug> bugs = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                bugs.add(fromFileString(line));
            }
        }
        return bugs;
    }

    @Override
    public void save(Bug bug) {
        List<Bug> bugs = findAll();
        bugs.add(bug);
        updateAll(bugs);
    }

    @Override
    public void updateAll(List<Bug> bugs) {
        List<String> lines = bugs.stream()
                .map(this::toFileString)
                .collect(Collectors.toList());
        writeLines(lines);
    }

    public Optional<Bug> findById(int id) {
        return findAll().stream().filter(b -> b.getId() == id).findFirst();
    }

    public void updateBugStatus(int id, BugStatus newStatus) {
        List<Bug> bugs = findAll();
        for (Bug b : bugs) {
            if (b.getId() == id) {
                b.setStatus(newStatus);
                break;
            }
        }
        updateAll(bugs);
    }

    public void assignBug(int bugId, int developerId) {
        List<Bug> bugs = findAll();
        for (Bug b : bugs) {
            if (b.getId() == bugId) {
                b.setAssigneeId(developerId);
                break;
            }
        }
        updateAll(bugs);
    }

    private String toFileString(Bug b) {
        return String.join("|",
                String.valueOf(b.getId()),
                b.getTitle(),
                b.getDescription(),
                b.getBugType(),
                b.getPriority(),
                b.getBugLevel(),
                b.getProjectName(),
                b.getBugDate(),
                b.getStatus().name(),
                String.valueOf(b.getAssigneeId()),
                String.valueOf(b.getReporterId()),
                b.getScreenshotPath()
        );
    }

    private Bug fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        return new Bug(
                Integer.parseInt(p[0]),
                p[1],
                p[2],
                p[3],
                p[4],
                p[5],
                p[6],
                p[7],
                BugStatus.valueOf(p[8]),
                Integer.parseInt(p[9]),
                Integer.parseInt(p[10]),
                p.length > 11 ? p[11] : ""
        );
    }
}