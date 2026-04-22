# Phase 5: Tester And Developer Workflows

## Goal

Finish the core bug lifecycle from reporting through assignment and resolution.

## Scope

- `bugtrackingsystem.gui.tester.TesterDashboardFrame`
- `bugtrackingsystem.gui.developer.DeveloperDashboardFrame`
- `bugtrackingsystem.controller.BugController`
- `bugtrackingsystem.service.BugService`
- Screenshot storage under `data/screenshots`

## Main Tasks

1. Keep tester bug reporting aligned with the existing `Bug` fields and file format.
2. Allow bugs to be created with project, priority, type, level, date, and screenshot metadata.
3. Save uploaded screenshots under `data/screenshots` and persist the stored path.
4. Let developers review bugs assigned to their user ID.
5. Let developers update bug status using the same status vocabulary agreed in Phase 2.
6. Keep bug updates visible in admin and manager views after refresh.

## Deliverables

- A working tester report flow
- A working developer update flow
- Shared bug lifecycle data visible across dashboards

## Completion Checklist

- [ ] Testers can submit new bugs.
- [ ] Screenshot paths are saved correctly.
- [ ] Developers can see assigned bugs.
- [ ] Developers can update bug status.
- [ ] Changes persist after restarting the app.
