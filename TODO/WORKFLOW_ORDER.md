# 🔄 Project Workflow: Sequential Execution Plan

> [!IMPORTANT] > **STRICT NO-MAVEN POLICY:** This project MUST be a standard Java Project. Do NOT use Maven, Gradle, or any build tools. No external JARs are required.

## 👥 Team Roles

- **Person 1:** Khodier
- **Person 2:** Fityan
- **Person 3:** Moaz
- **Person 4:** Bekhet
- **Person 5:** Salah
- **Person 6:** Michael

This document outlines the critical path for the project. **Follow these phases strictly in order.**
Do not proceed to the next phase until the current phase's "Checklist" is 100% complete.

---

## 1️⃣ Phase 1: Project Initialization (Person 1 - Khodier)

**Role:** Lead / Architect

**Actions:**

1.  Create the root project folder `BugTrackingSystem`.
2.  Create the standard directory structure:
    - `src/` (Source code)
    - `data/` (Text storage)
3.  Create the base package: `com.bugtracker`.

**✅ Checklist (Definition of Done):**

- [ ] Folder structure exists.
- [ ] `data` folder is empty but present.
- [ ] Team has been notified that the repo is created.

---

## 2️⃣ Phase 2: Dependency Acquisition (Person 6 - Michael)

**Role:** Infrastructure Engineer

**Actions:**

1.  Download `gson-2.10.1.jar` (or latest version).
2.  Send the JAR file to Person 1 (via email, chat, or USB).

**✅ Checklist (Definition of Done):**

- [ ] Person 1 has received the physical `.jar` file.

---

## 3️⃣ Phase 3: Core Architecture & Styling (Person 1 - Khodier)

**Role:** Team Lead / Architect
**⚠️ CRITICAL PHASE:** The team is BLOCKED until this is finished.

**Actions:**

1.  **Project Setup:** Ensure `data` folder exists.
2.  **Create Style Engine:** Implement `com.bugtracker.util.StyleUtils`.
    - Define `applyDarkTheme()` to set `UIManager` colors (Background `#2E2E2E`, Text `#FFFFFF`).
3.  **Create Components:** Implement `com.bugtracker.ui.components.ModernButton`.
4.  **Push/Share:** Commit these changes so the team can pull them.

**✅ Checklist (Definition of Done):**

- [ ] `StyleUtils` class exists and compiles.
- [ ] `ModernButton` class exists.
- [ ] `User` entity class exists with fields (id, username, password, role), constructor, getters, and setters.
- [ ] `Bug` entity class exists with fields (id, title, description, status, priority, etc.), constructor, getters, and setters.
- [ ] Data storage logic understood.
- [ ] Team has pulled the latest code.

---

## 4️⃣ Phase 4: Parallel Development (The Team)

**Status:** UNBLOCKED. Everyone works simultaneously now.

### 👤 Person 2 (Fityan) - Admin Module

- **Task:** Build `com.bugtracker.ui.dashboard.AdminDashboard`.
- **Requirement:** Use `StyleUtils` for colors.

### 👤 Person 3 (Moaz) - PM Module

- **Task:** Build `com.bugtracker.ui.dashboard.PMDashboard`.
- **Requirement:** Use `StyleUtils` for colors.

### 👤 Person 4 (Bekhet) - Developer Module

- **Task:** Build `com.bugtracker.ui.dashboard.DeveloperDashboard`.
- **Requirement:** Use `StyleUtils` for colors.

### 👤 Person 5 (Salah) - Tester Module

- **Task:** Build `com.bugtracker.ui.dashboard.TesterDashboard`.
- **Requirement:** Use `StyleUtils` for colors.

### 👤 Person 6 (Michael) - Data Layer

- **Task:** Implement `com.bugtracker.repository.FileRepository`.
- **Requirement:** Ensure `users.txt` and `bugs.txt` are created in `data/` using pipe delimiter.

**✅ Checklist (Definition of Done):**

- [ ] All 4 Dashboards are created (even if empty).
- [ ] `FileRepository` can save a file to `data/`.

---

## 5️⃣ Phase 5: Final Integration (Person 1 - Khodier)

**Role:** Team Lead

**Actions:**

1.  Implement `com.bugtracker.ui.LoginFrame`.
2.  Implement `com.bugtracker.Main`.
3.  **Routing Logic:**
    - If `User.role == ADMIN` -> Open `AdminDashboard`.
    - If `User.role == TESTER` -> Open `TesterDashboard`.
    - (etc...)

**✅ Checklist (Definition of Done):**

- [ ] Running `Main.java` opens the Login Screen.
- [ ] Logging in opens the correct Dashboard.
- [ ] The app looks "Dark Mode" consistent.

---

## 🛑 Troubleshooting

- **"UI looks ugly/default":** You forgot to call `StyleUtils.applyDarkTheme()` in `Main.java`.
