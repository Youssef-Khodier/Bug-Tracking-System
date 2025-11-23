# TODO: Person 1 (Khodier) - Team Lead & Core Architecture

**Role:** Team Lead, Core Architect, and UI/UX Designer
**Focus:** Project Setup, Manual Dependency Management, Custom UI Styling (Dark Theme), and Authentication.

## Prerequisites & Requirements

- **Java Development Kit (JDK) 17+** installed.
- **IDE:** IntelliJ IDEA, Eclipse, or NetBeans.
- **Manual Build:** NO Maven or Gradle. You will manage the `lib` folder.
- **Manual Styling:** NO FlatLaf. You must implement a custom "Dark Mode" using standard Swing `UIManager` customization.

## Phase 1: Project Initialization & Structure

1.  **Create Project Structure:**

    - Create a new Java project (non-Maven/Gradle).
    - Create the following package structure in `src`:
      - `com.bugtracker`
        - `main`
        - `entity`
        - `repository`
        - `service`
        - `util`
        - `ui`
          - `components`
          - `dashboard`
    - Create a root folder named `lib` for external JARs.
    - Create a root folder named `data` for JSON storage.

2.  **Dependency Management:**
    - **Action:** You are responsible for the `lib` folder.
    - **Task:** Coordinate with Person 6 to ensure `gson-2.10.1.jar` (or similar) is downloaded and placed in `lib`.
    - **Task:** Add `lib/gson-*.jar` to your IDE's Build Path / Classpath.

## Phase 2: Custom UI Styling (The "Dark Mode" Engine)

**Goal:** Create a premium Dark Theme without using external libraries like FlatLaf.

1.  **Create `StyleUtils` Class:**

    - **Location:** `com.bugtracker.util.StyleUtils`
    - **Task:** Create a static method `applyDarkTheme()` that sets `UIManager` properties.
    - **Properties to Set:**
      - **Backgrounds:** Set `Panel.background`, `Frame.background`, `Label.background`, etc., to a dark gray (e.g., `#2E2E2E`).
      - **Foregrounds:** Set `Label.foreground`, `Button.foreground`, etc., to white or light gray (e.g., `#E0E0E0`).
      - **Buttons:** Customize `Button.background` (e.g., `#3C3F41`), `Button.border` (LineBorder), and `Button.focus` (remove focus painting).
      - **TextFields:** Set `TextField.background` (darker gray `#252526`), `TextField.foreground` (white), and `TextField.caretForeground` (white).
      - **Tables:** Set `Table.background`, `Table.foreground`, `Table.selectionBackground` (blue accent), `Table.gridColor`.
    - **Fonts:** Set a modern font (e.g., "Segoe UI", "Arial", size 14) for all components.

2.  **Create `ModernButton` Component:**
    - **Location:** `com.bugtracker.ui.components.ModernButton`
    - **Task:** Extend `JButton`.
    - **Logic:** Apply your custom colors, padding, and hover effects (using `MouseListener`) to make buttons look modern and "flat".

## Phase 3: Core Entities & Authentication

1.  **Create `User` Entity:**

    - **Location:** `com.bugtracker.entity.User`
    - **Fields:** `id` (int), `username` (String), `password` (String), `role` (Enum: ADMIN, PROJECT_MANAGER, DEVELOPER, TESTER).
    - **Methods:** Constructor, Getters, Setters.

2.  **Create `Bug` Entity:**

    - **Location:** `com.bugtracker.entity.Bug`
    - **Fields:** `id` (int), `title` (String), `description` (String), `bugType` (String), `priority` (String), `bugLevel` (String), `projectName` (String), `bugDate` (String), `status` (Enum: OPEN, IN_PROGRESS, COMPLETED), `assigneeId` (int), `reporterId` (int), `screenshotPath` (String).

3.  **Implement `LoginFrame`:**

    - **Location:** `com.bugtracker.ui.LoginFrame`
    - **Task:** Create a `JFrame`.
    - **UI:** Use `StyleUtils` to apply the dark theme.
    - **Layout:** Use `GridBagLayout` or `null` layout (if careful) for a centered login box.
    - **Fields:** Username field, Password field, Login button.
    - **Logic:** Hardcode a check for now (e.g., admin/admin) OR call `UserService.login()` (stub this out).

4.  **Implement `Main` Class:**
    - **Location:** `com.bugtracker.main.Main`
    - **Task:**
      1. Call `StyleUtils.applyDarkTheme()`.
      2. Launch `LoginFrame`.

## Phase 4: Integration & Leadership

1.  **Code Review:**

    - Review code from Persons 2-6 to ensure they are using your `StyleUtils` and `ModernButton` classes.
    - Ensure no one is using default Swing colors.

2.  **Merge & Run:**
    - You are responsible for the final `main` method entry point.
