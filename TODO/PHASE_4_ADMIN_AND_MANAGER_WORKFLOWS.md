# Phase 4: Admin And Manager Workflows

## Goal

Complete the management side of the application using the current admin and manager screens.

## Scope

- `bugtrackingsystem.gui.admin.AdminDashboardFrame`
- `bugtrackingsystem.gui.manager.ManagerDashboardFrame`
- `bugtrackingsystem.controller.UserController`
- `bugtrackingsystem.controller.BugController`
- `bugtrackingsystem.service.ProjectService`

## Main Tasks

1. Keep admin user management working for create, edit, and delete actions.
2. Keep the admin bug registry table synchronized with the latest bug data.
3. Review manager dashboard statistics for total, pending, and closed or completed bugs.
4. Align manager reporting with the same bug status names used in repositories and services.
5. Surface project context where useful so management views are tied to real project records instead of only raw IDs.
6. Keep both dashboards visually aligned with the shared styling system.

## Deliverables

- Admin CRUD for users
- Admin visibility into the bug registry
- Manager reporting that reflects real bug data

## Completion Checklist

- [ ] Admin can add users.
- [ ] Admin can edit users.
- [ ] Admin can delete users.
- [ ] Admin can inspect the current bug list.
- [ ] Manager statistics match stored data.
- [ ] Manager views use consistent status vocabulary.
