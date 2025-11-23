# TODO: Person 4 (Bekhet) - Developer Module Developer

**Role:** Developer Module Specialist  
**Focus:** Fixing Bugs, Changing Status, and the Developer Screen.

## Before You Start

- You MUST use `StyleUtils` and `ModernButton` from Person 1.
- Person 6 creates the Repository. You will use `BugRepository` to save changes.
- Check if Person 5 already created `BugService`. If yes, add your methods to the same class.

---

## Phase 1: Create Bug Workflow Logic

**Goal:** Build service methods to find assigned bugs and update their status.

### Step 1: Open BugService Class
**Purpose:** To set up the service for managing bug operations.

1. Open the `service` package.
2. Open the `BugService` class (it should already exist).
3. Add imports: `java.util.List`, `java.util.ArrayList`, and `com.bugtracker.entity.Bug`.
4. Create a private variable called `bugRepository` of type `BugRepository`.
5. Create a constructor that initializes `bugRepository` to a new `BugRepository()`.

### Step 2: Create getAssignedBugs Method
**Purpose:** To retrieve only the bugs assigned to the logged-in developer.

1. Create a public method called `getAssignedBugs` that takes `int developerId` and returns `List<Bug>`.
2. Inside, call `bugRepository.findAll()` and store in a variable called `allBugs`.
3. Create a new empty ArrayList of type Bug called `myBugs`.
4. Create a for loop that goes through each bug in `allBugs`.
5. Inside the loop, check if the bug's assigneeId equals the developerId parameter.
6. If yes, add this bug to the `myBugs` list.
7. After the loop, return `myBugs`.

### Step 3: Create updateBugStatus Method
**Purpose:** To change the status of a bug (e.g., from Open to In Progress).

1. Create a public method called `updateBugStatus` that takes `int bugId` and `BugStatus newStatus`, returns void.
2. Inside, call `bugRepository.findById(bugId)` and store in a variable called `bug`.
3. Check if bug is null. If yes, print an error message and return.
4. Create a validation check: if the bug's current status is COMPLETED and newStatus is OPEN, throw a RuntimeException with message "Cannot reopen a completed bug".
5. Set the bug's status to newStatus by calling `bug.setStatus(newStatus)`.
6. Call `bugRepository.save(bug)` to save the change to the file.

### Step 4: Create addComment Method (Optional)
**Purpose:** To add a comment to a bug for better communication.

1. Create a public method called `addComment` that takes `int bugId` and `String comment`, returns void.
2. Call `bugRepository.findById(bugId)` and store in `bug`.
3. Check if bug is null. If yes, return.
4. Get the bug's current description.
5. Create a new String that combines the current description, a newline, "[Comment]: ", and the comment.
6. Set this new String as the bug's description.
7. Call `bugRepository.save(bug)`.

---

## Phase 2: Build Developer Dashboard Screen

**Goal:** Create the visual window where developers see and work on their assigned bugs.

### Step 1: Open DeveloperDashboard Class
**Purpose:** To set up the main window for the Developer.

1. Open the `ui.dashboard` package.
2. Open the `DeveloperDashboard` class (it should already exist).
3. Make sure it extends `JFrame`.
4. Add imports for Swing, AWT, and `com.bugtracker.entity.*`.
5. Create a private variable called `bugService` of type `BugService`.
6. Create a private variable called `currentDeveloper` of type `User`.
7. Create a private variable called `bugList` of type `JList<Bug>`.
8. Create a private variable called `titleLabel` of type `JLabel`.
9. Create a private variable called `descArea` of type `JTextArea`.
10. Create a private variable called `statusBox` of type `JComboBox<BugStatus>`.

### Step 2: Build the Constructor
**Purpose:** To initialize the dashboard and load user-specific data.

1. Create a public constructor that takes `User developer` as parameter.
2. Store the developer parameter in `currentDeveloper`.
3. Initialize `bugService` to a new `BugService()`.
4. Call `setTitle` with "Developer Dashboard - " plus the developer's username.
5. Call `setSize` with width 900 and height 600.
6. Call `setDefaultCloseOperation` with `JFrame.EXIT_ON_CLOSE`.
7. Call `setLayout` with `new BorderLayout()`.

### Step 3: Create Bug List on Left Side
**Purpose:** To show a list of bugs assigned to the developer.

1. Call `bugService.getAssignedBugs(currentDeveloper.getId())` and store in a variable called `bugs`.
2. Create an array or Vector from the bugs list.
3. Create `bugList` as a new JList initialized with that array.
4. Add a ListSelectionListener to bugList.
5. Inside the listener, check if the selection is adjusting (getValueIsAdjusting returns false).
6. Get the selected bug from bugList.
7. Call a method to display bug details (we'll create this next).
8. Create a JScrollPane wrapping bugList.
9. Add the scroll pane to the frame's WEST.

### Step 4: Create Details Panel on Right Side
**Purpose:** To display full details of the selected bug.

1. Create a method called `displayBugDetails` that takes `Bug bug` and returns void.
2. Inside, set titleLabel's text to bug's title.
3. Set descArea's text to bug's description.
4. Set statusBox's selected item to bug's status.
5. In the constructor, create a JPanel called `detailsPanel` with GridLayout (7 rows, 1 column, gap 10).
6. Create titleLabel with empty text.
7. Create a JLabel "Description:".
8. Create descArea with 10 rows and 30 columns, make it non-editable.
9. Create a JLabel "Status:".
10. Create statusBox with BugStatus enum values: OPEN, IN_PROGRESS, COMPLETED.
11. Create a ModernButton called `saveBtn` with text "Save Changes".
12. Create a ModernButton called `logoutBtn` with text "Logout".
13. Add all components to detailsPanel in order.
14. Add detailsPanel to the frame's CENTER.

### Step 5: Add Save Button Logic
**Purpose:** To save changes made to the bug status.

1. Add an ActionListener to saveBtn.
2. Inside, get the selected bug from bugList (check if selection is not empty).
3. If no selection, show a message and return.
4. Get the selected status from statusBox.
5. Call `bugService.updateBugStatus` with the bug's ID and the new status.
6. Show a message dialog saying "Bug status updated!".
7. Reload the bug list by calling the method that populates it.
8. Clear the details panel.

### Step 6: Add Logout Button
**Purpose:** To allow the developer to exit the dashboard.

1. Add an ActionListener to logoutBtn.
2. Inside, dispose the current frame (close the dashboard).

---

## Phase 3: Testing Your Work

1. Make sure Person 5 or Person 6 has created some bugs assigned to a developer in the data file.
2. Run the application and login as a Developer (the one who has bugs assigned).
3. Check if the bug list on the left shows your assigned bugs.
4. Click on a bug and verify details appear on the right.
5. Change the status from OPEN to IN_PROGRESS and click Save.
6. Verify the change is saved (close and reopen app, or check the JSON file).
