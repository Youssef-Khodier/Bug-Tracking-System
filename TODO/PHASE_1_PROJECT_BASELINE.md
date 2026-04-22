# Phase 1: Project Baseline

## Goal

Stabilize the current NetBeans Java project structure before adding or changing features.

## Scope

- Project root and NetBeans metadata
- Shared styling in `bugtrackingsystem.util.StyleUtils`
- Shared GUI foundation in `bugtrackingsystem.gui`
- Startup configuration and entry flow

## Main Tasks

1. Confirm the active package structure is `src/bugtrackingsystem`.
2. Keep the project as a standard Java application with no Maven or Gradle migration.
3. Review `nbproject/project.properties` and align the startup flow with `bugtrackingsystem.gui.auth.LoginFrame`.
4. Keep shared colors, fonts, button styling, and table styling inside `StyleUtils`.
5. Review `BaseDashboardFrame` and shared frame behavior so all dashboards stay visually consistent.
6. Identify generated artifacts committed to the repository such as `.class` files under `src/`, `bin/`, or `build/` and treat them as cleanup work, not source of truth.

## Deliverables

- A clear application entry path
- Shared styling rules that all dashboards use
- A stable base for later data and role workflow phases

## Completion Checklist

- [ ] The project builds in NetBeans on JDK 17.
- [ ] The intended startup screen is the login screen.
- [ ] Shared styling lives in `StyleUtils`.
- [ ] Shared dashboard behavior is reusable.
- [ ] Generated build artifacts are identified for later cleanup.
