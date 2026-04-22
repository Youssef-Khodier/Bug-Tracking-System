# Phase 3: Authentication And Navigation

## Goal

Make login, role resolution, and dashboard routing reliable for all supported user types.

## Scope

- `bugtrackingsystem.gui.auth.LoginFrame`
- `bugtrackingsystem.controller.LoginController`
- `bugtrackingsystem.service.AuthService`
- Role-based dashboard opening logic
- Shared logout flow

## Main Tasks

1. Validate `LoginFrame` input handling for username and password.
2. Confirm `LoginController` and `AuthService` return the correct `User` record from `users.txt`.
3. Route authenticated users to the correct dashboard:
   - `AdminDashboardFrame`
   - `ManagerDashboardFrame`
   - `DeveloperDashboardFrame`
   - `TesterDashboardFrame`
4. Ensure invalid credentials produce a clear error message.
5. Keep logout behavior consistent so each role returns to the login screen.
6. Confirm the configured run target does not bypass login and open a dashboard directly.

## Deliverables

- A complete login-to-dashboard flow
- Reliable role routing
- Clean return-to-login behavior

## Completion Checklist

- [ ] Valid users can sign in.
- [ ] Invalid users cannot sign in.
- [ ] Each role opens the correct dashboard.
- [ ] Logout returns to the login screen.
- [ ] Startup configuration matches the intended user flow.
