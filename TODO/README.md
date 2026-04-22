# Bug Tracking System Implementation Plan

This folder now documents the implementation plan for the current project, not person-based assignments.

## Project Context

- Application type: Java Swing desktop application
- Project type: NetBeans standard Java project
- Java version: JDK 17
- Package root: `src/bugtrackingsystem`
- Data storage: text files inside `data/`
- Current roles in code: `Admin`, `Manager`, `Developer`, `Tester`

## Current Code Areas

- `src/bugtrackingsystem/controller`: login, user, and bug coordination
- `src/bugtrackingsystem/gui`: shared frame base and role dashboards
- `src/bugtrackingsystem/model`: `User`, `Bug`, `Project`, `Comment`
- `src/bugtrackingsystem/service`: file persistence and business services
- `src/bugtrackingsystem/util`: shared UI styling
- `data/`: `users.txt`, `bugs.txt`, `projects.txt`, `email.txt`, and screenshots

## Phase Documents

1. `PHASE_1_PROJECT_BASELINE.md`
2. `PHASE_2_DATA_AND_DOMAIN.md`
3. `PHASE_3_AUTH_AND_NAVIGATION.md`
4. `PHASE_4_ADMIN_AND_MANAGER_WORKFLOWS.md`
5. `PHASE_5_TESTER_AND_DEVELOPER_WORKFLOWS.md`
6. `PHASE_6_QA_AND_RELEASE_READINESS.md`

## Working Rules

- Complete phases in order unless a later task is clearly independent.
- Keep all planning compatible with the current `bugtrackingsystem` package layout.
- Do not reintroduce the old `com.bugtracker` structure in new work.
- Keep file-based persistence as the source of truth for this project.
- Use the role names and dashboard names that already exist in the codebase.
