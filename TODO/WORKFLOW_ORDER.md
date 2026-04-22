# Project Workflow: Phased Implementation Order

This implementation plan is aligned with the current NetBeans project under `src/bugtrackingsystem`.

Important constraints:

- Keep the project as a standard Java project.
- Do not switch to Maven or Gradle.
- Keep file persistence in `data/`.
- Use the current role names: `Admin`, `Manager`, `Developer`, `Tester`.

Follow the phases in order. A later phase can start early only when it does not depend on unfinished work from an earlier phase.

## Phase 1: Project Baseline

Goal: stabilize the project structure and shared UI foundation.

Key tasks:

1. Confirm the active source root is `src/bugtrackingsystem`.
2. Keep shared styling inside `bugtrackingsystem.util.StyleUtils`.
3. Confirm the correct startup flow should open `bugtrackingsystem.gui.auth.LoginFrame`.
4. Review shared GUI code such as `BaseDashboardFrame` and common button and table styling.
5. Identify generated artifacts that should not drive implementation decisions, especially compiled `.class` files stored in source folders.

Checklist:

- [ ] The project builds as a NetBeans Java project.
- [ ] Shared styling is centralized and reusable.
- [ ] The intended entry screen is the login screen.
- [ ] Phase 2 references the current package names only.

## Phase 2: Data And Domain Layer

Goal: keep the data model and persistence layer consistent with the existing application behavior.

Key tasks:

1. Validate the models in `bugtrackingsystem.model`.
2. Confirm the file contracts for `users.txt`, `bugs.txt`, `projects.txt`, and `email.txt`.
3. Review `Repository`, `UserRepository`, `BugRepository`, and `FileRepository` responsibilities.
4. Standardize bug field usage across model, repository, and UI.
5. Standardize bug status values so dashboards and services read the same vocabulary.

Checklist:

- [ ] User, bug, and project records serialize consistently.
- [ ] Repository classes read and write the same file formats.
- [ ] Status values are documented and consistent.
- [ ] Project data is available for role-based screens.

## Phase 3: Authentication And Navigation

Goal: make login and role routing reliable.

Key tasks:

1. Validate `LoginController` and `AuthService` login behavior.
2. Keep `LoginFrame` as the main entry for the user flow.
3. Route users to `AdminDashboardFrame`, `ManagerDashboardFrame`, `DeveloperDashboardFrame`, or `TesterDashboardFrame` based on role.
4. Keep logout behavior consistent so each dashboard returns to the login screen.
5. Add clear handling for invalid credentials and unsupported role values.

Checklist:

- [ ] A valid user can sign in.
- [ ] Each role opens the correct dashboard.
- [ ] Logout returns to the login screen.
- [ ] Invalid login attempts are handled cleanly.

## Phase 4: Admin And Manager Workflows

Goal: complete the administrative and reporting parts of the product.

Key tasks:

1. Complete user CRUD in `AdminDashboardFrame` and related controllers.
2. Keep the admin bug registry view accurate and refreshable.
3. Use `ProjectService` and bug data to support project oversight.
4. Make manager statistics reliable for total, pending, and closed or completed bugs.
5. Ensure both dashboards use the shared styling utilities.

Checklist:

- [ ] Admin can create, edit, and delete users.
- [ ] Admin can inspect the current bug registry.
- [ ] Manager can review bug counts and project progress.
- [ ] Manager reporting reflects the same status rules used elsewhere.

## Phase 5: Tester And Developer Workflows

Goal: complete the day-to-day bug reporting and bug resolution loop.

Key tasks:

1. Keep bug reporting inside the tester flow with screenshot support.
2. Save screenshots under `data/screenshots` and persist the path in bug records.
3. Let developers view assigned bugs and update bug status.
4. Keep bug assignment, project selection, and role visibility consistent.
5. Make sure UI changes write back to the same file-based persistence layer.

Checklist:

- [ ] Testers can create bugs successfully.
- [ ] Bug screenshots are stored and referenced correctly.
- [ ] Developers can review assigned bugs.
- [ ] Developers can move bugs through the allowed status flow.

## Phase 6: QA And Release Readiness

Goal: verify the full application flow and reduce project friction before release.

Key tasks:

1. Run role-based smoke tests from login through logout.
2. Validate persistence by restarting the app and reloading saved data.
3. Confirm all dashboards can handle empty and populated data files.
4. Review build outputs and generated files to separate source from artifacts.
5. Finalize contributor instructions and cleanup tasks.

Checklist:

- [ ] End-to-end role flows are tested.
- [ ] Data persists correctly between runs.
- [ ] Generated artifacts are identified for cleanup or ignore rules.
- [ ] The TODO folder reflects the final phased plan.

## Reference Files

- `PHASE_1_PROJECT_BASELINE.md`
- `PHASE_2_DATA_AND_DOMAIN.md`
- `PHASE_3_AUTH_AND_NAVIGATION.md`
- `PHASE_4_ADMIN_AND_MANAGER_WORKFLOWS.md`
- `PHASE_5_TESTER_AND_DEVELOPER_WORKFLOWS.md`
- `PHASE_6_QA_AND_RELEASE_READINESS.md`
