# TODO: Person 3 (Moaz) - Project Manager Module Developer

**Role:** Project Manager (PM) Module Specialist  
**Focus:** Creating Reports, Checking Performance, and the PM Screen.

## Before You Start

- You MUST use `StyleUtils` and `ModernButton` from Person 1.
- Person 6 creates the Repository. You will use `BugRepository` to read bug data.
- Look at the `Bug` class to understand its fields, especially the `status` field.

---

## Phase 1: Create Reporting Logic

**Goal:** Build service methods that calculate statistics about bugs.

### Step 1: Open ReportService Class

**Purpose:** To handle the logic for generating project reports.

1. Open the `service` package.
2. Open the `ReportService` class (it should already exist).
3. Add imports: `java.util.Map`, `java.util.HashMap`, `java.util.List`, `java.io.*`, and `com.bugtracker.entity.Bug`.
4. Create a private variable called `bugRepository` of type `BugRepository`.
5. Create a constructor that sets `bugRepository` to a new `BugRepository()`.

### Step 2: Create getBugCounts Method

**Purpose:** To calculate the number of bugs in each status category.

1. Create a public method called `getBugCounts` that returns `Map<String, Integer>`.
2. Inside, create a variable called `stats` of type `HashMap<String, Integer>`.
3. Call `bugRepository.findAll()` and store in a variable called `allBugs`.
4. Create three integer variables: `openCount`, `inProgressCount`, `completedCount`, all starting at 0.
5. Create a for loop that goes through each bug in `allBugs`.
6. Inside the loop, check the bug's status.
7. If status is OPEN, increment `openCount` by 1.
8. If status is IN_PROGRESS, increment `inProgressCount` by 1.
9. If status is COMPLETED, increment `completedCount` by 1.
10. After the loop, put "Open" and `openCount` into the stats map.
11. Put "In Progress" and `inProgressCount` into the stats map.
12. Put "Completed" and `completedCount` into the stats map.
13. Return the stats map.

### Step 3: Create getDeveloperPerformance Method

**Purpose:** To track how many bugs each developer has completed.

1. Create a public method called `getDeveloperPerformance` that returns `Map<String, Integer>`.
2. Create a variable called `performance` of type `HashMap<String, Integer>`.
3. Call `bugRepository.findAll()` and store in `allBugs`.
4. Create a for loop through all bugs.
5. Inside the loop, check if the bug's status equals COMPLETED.
6. If yes, get the bug's assigneeId.
7. Convert the assigneeId to a String (like "Developer ID: " + assigneeId). Note: For now, using the ID is fine. Later we can look up the actual developer name from UserRepository.
8. Check if this developer name already exists in the performance map.
9. If it exists, get the current count, add 1, and put it back.
10. If it doesn't exist, put the developer name with count 1.
11. Return the performance map.

### Step 4: Create generateTextReport Method

**Purpose:** To save the project statistics to a text file.

1. Create a public method called `generateTextReport` that takes `String filePath` and returns void.
2. Wrap the code in a try-catch block for IOException.
3. Inside the try, create a FileWriter using the filePath.
4. Call `getBugCounts()` and store the result.
5. Write to the file: "Open Bugs: " plus the count from the map.
6. Write a newline character.
7. Write "In Progress Bugs: " plus the count from the map.
8. Write a newline character.
9. Write "Completed Bugs: " plus the count from the map.
10. Write two newlines.
11. Call `getDeveloperPerformance()` and store the result.
12. Write "Developer Performance:" and a newline.
13. Loop through the performance map and write each developer name and their count.
14. Close the FileWriter in the finally block or use try-with-resources.

---

## Phase 2: Build PM Dashboard Screen

**Goal:** Create the visual window where the PM sees project statistics.

### Step 1: Open PMDashboard Class

**Purpose:** To set up the main window for the Project Manager.

1. Open the `ui.dashboard` package.
2. Open the `PMDashboard` class (it should already exist).
3. Make sure it extends `JFrame`.
4. Add necessary imports for Swing, AWT, and `com.bugtracker.ui.components.ModernButton`.
5. Create a private variable called `reportService` of type `ReportService`.

### Step 2: Build the Constructor

**Purpose:** To initialize the dashboard layout and components.

1. Create a public constructor.
2. Call `setTitle` with "Project Manager Dashboard".
3. Call `setSize` with width 900 and height 700.
4. Call `setDefaultCloseOperation` with `JFrame.EXIT_ON_CLOSE`.
5. Call `setLayout` with `new BorderLayout()`.
6. Initialize `reportService` to a new `ReportService()`.

### Step 3: Create Statistics Cards at Top

**Purpose:** To display key project metrics at a glance.

1. Create a method called `createCard` that takes `String title` and `int count`, returns `JPanel`.
2. Inside, create a JPanel called `card` with BorderLayout.
3. Set the card's background color to a dark gray (use `Color.decode("#404040")`).
4. Create a JLabel called `titleLabel` with the title text.
5. Create a JLabel called `countLabel` with the count converted to String.
6. Set countLabel's font to a larger, bold font.
7. Add titleLabel to the NORTH of card.
8. Add countLabel to the CENTER of card.
9. Return the card.
10. In the constructor, create a JPanel called `topPanel` with GridLayout (1 row, 3 columns, gap 20).
11. Call `reportService.getBugCounts()` and store the result.
12. Create three cards using createCard: one for "Open", one for "In Progress", one for "Completed".
13. Add all three cards to topPanel.
14. Add topPanel to the frame's NORTH.

### Step 4: Create Performance Display in Center

**Purpose:** To show detailed performance stats for developers.

1. In the constructor, create a JTextArea called `performanceArea`.
2. Set it to non-editable.
3. Call `reportService.getDeveloperPerformance()` and store the result.
4. Check if the performance map is not null and not empty.
5. Append text to performanceArea: "Developer Performance Report" and a newline.
6. Loop through the performance map and append each developer's name and count.
7. If the map is empty, append "No completed bugs yet."
8. Create a JScrollPane wrapping performanceArea.
9. Add the scroll pane to the frame's CENTER.

### Step 5: Create Export Button at Bottom

**Purpose:** To allow the PM to save the report to a file.

1. In the constructor, create a JPanel called `bottomPanel`.
2. Create a ModernButton called `exportBtn` with text "Export Report".
3. Add an ActionListener to exportBtn.
4. Inside the listener, create a JFileChooser.
5. Show the save dialog and check if user clicked OK.
6. If yes, get the selected file's absolute path.
7. Call `reportService.generateTextReport` with that path.
8. Show a message dialog saying "Report saved successfully!".
9. Add exportBtn to bottomPanel.
10. Add bottomPanel to the frame's SOUTH.

---

## Phase 3: Testing Your Work

1. Make sure Person 6 has created the BugRepository and there are some bugs in the data file.
2. Run the application and login as Project Manager.
3. Check if the three stat cards show correct numbers.
4. Check if the performance area shows developer information.
5. Click "Export Report" and save to a file.
6. Open the saved file and verify it contains all the statistics.
