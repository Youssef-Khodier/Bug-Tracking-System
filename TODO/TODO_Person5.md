# TODO: Person 5 (Salah) - Tester Module Developer

**Role:** Tester Module Specialist  
**Focus:** Reporting Bugs, Uploading Screenshots, and the Tester Screen.

## Before You Start

- You MUST use `StyleUtils` and `ModernButton` from Person 1.
- Person 6 creates the Repository. You will use `BugRepository` to save bugs.
- Check if Person 4 already created `BugService`. If yes, add your methods to the same class.

---

## Phase 1: Create Bug Creation Logic

**Goal:** Build service methods to create new bugs and handle screenshot files.

### Step 1: Open BugService Class

**Purpose:** To set up the service for bug creation and management.

1. Open the `service` package.
2. Open the `BugService` class (it should already exist).
3. Add imports: `java.util.List`, `java.io.File`, `java.nio.file.*`, and `com.bugtracker.entity.Bug`.
4. Create a private variable called `bugRepository` of type `BugRepository`.
5. Create a constructor that initializes `bugRepository` to a new `BugRepository()`.

### Step 2: Create createBug Method

**Purpose:** To save a newly reported bug to the system.

1. Create a public method called `createBug` that takes `Bug newBug` and returns void.
2. Inside, call `bugRepository.save(newBug)`.

### Step 3: Create getAllBugs Method

**Purpose:** To retrieve a list of all reported bugs.

1. Create a public method called `getAllBugs` that returns `List<Bug>`.
2. Inside, call `bugRepository.findAll()` and return the result.

### Step 4: Create saveScreenshot Method

**Purpose:** To handle the file upload and saving of bug screenshots.

1. Create a public method called `saveScreenshot` that takes `File sourceFile` and `int bugId`, returns `String`.
2. Create a File variable called `destDir` pointing to "data/screenshots".
3. Check if destDir doesn't exist. If true, call `destDir.mkdirs()` to create the folder.
4. Create a String variable called `fileName` that combines "bug\_", the bugId, an underscore, current time in milliseconds, and ".png".
5. Create a File variable called `destFile` using destDir and fileName.
6. Wrap the next steps in a try-catch block for IOException.
7. Inside the try, use `Files.copy` to copy sourceFile's path to destFile's path with REPLACE_EXISTING option.
8. Return destFile's absolute path as a String.
9. In the catch block, print the error and return null or empty string.

---

## Phase 2: Build Tester Dashboard Screen

**Goal:** Create the visual window where testers report bugs and view all bugs.

### Step 1: Open TesterDashboard Class

**Purpose:** To set up the main window for the Tester.

1. Open the `ui.dashboard` package.
2. Open the `TesterDashboard` class (it should already exist).
3. Make sure it extends `JFrame`.
4. Add imports for Swing, AWT, and `com.bugtracker.entity.*`.
5. Create a private variable called `bugService` of type `BugService`.
6. Create a private variable called `tableModel` of type `DefaultTableModel`.
7. Create a private variable called `currentTester` of type `User`.

### Step 2: Build the Constructor

**Purpose:** To initialize the dashboard layout.

1. Create a public constructor that takes `User tester` as parameter.
2. Store the tester in `currentTester`.
3. Initialize `bugService` to a new `BugService()`.
4. Call `setTitle` with "Tester Dashboard".
5. Call `setSize` with width 900 and height 600.
6. Call `setDefaultCloseOperation` with `JFrame.EXIT_ON_CLOSE`.
7. Call `setLayout` with `new BorderLayout()`.

### Step 3: Create Top Panel with Report Button

**Purpose:** To provide a quick way to report new bugs.

1. Create a JPanel called `topPanel`.
2. Create a ModernButton called `reportBtn` with text "Report New Bug".
3. Add an ActionListener to reportBtn that opens a BugReportDialog (we'll create this next).
4. Add reportBtn to topPanel.
5. Add topPanel to the frame's NORTH.

### Step 4: Create Bug Table in Center

**Purpose:** To display all bugs in the system for review.

1. Create a String array called `columnNames` with values: "ID", "Title", "Status", "Priority".
2. Create `tableModel` as a new DefaultTableModel with columnNames and 0 rows.
3. Create a JTable called `bugTable` using tableModel.
4. Create a JScrollPane wrapping bugTable.
5. Add the scroll pane to the frame's CENTER.
6. Create a method called `loadBugs` that returns void.
7. Inside loadBugs, call `tableModel.setRowCount(0)` to clear.
8. Call `bugService.getAllBugs()` and store in `bugs`.
9. Loop through bugs and add each bug's ID, title, status, and priority as a row to tableModel.
10. Call loadBugs at the end of the constructor.

### Step 5: Open BugReportDialog Class

**Purpose:** To create a form for entering new bug details.

1. In the `ui.dialog` package, open the `BugReportDialog` class (it should already exist).
2. Make sure it extends `JDialog`.
3. Create a constructor that takes `JFrame parent` and `User tester` as parameters.
4. Add imports for Swing, AWT, File, and `com.bugtracker.entity.*`.
5. Create a private variable called `selectedFile` of type `File`.
6. Create a private variable called `bugService` of type `BugService`.
7. Call `setTitle` with "Report New Bug".
8. Call `setModal` with true.
9. Call `setSize` with width 500 and height 600.
10. Call `setLayout` with `new GridLayout` of 8 rows, 2 columns, gaps 10.
11. Initialize `bugService` to a new `BugService()`.

### Step 6: Add Form Fields to Dialog

**Purpose:** To collect all necessary information about the bug.

1. Create a JTextField called `titleField`.
2. Create a JTextArea called `descArea` with 5 rows and 20 columns.
3. Create a JComboBox called `bugTypeBox` with values: "Functional", "UI", "Performance", "Security", "Other".
4. Create a JComboBox called `priorityBox` with values: "Low", "Medium", "High", "Critical".
5. Create a JComboBox called `bugLevelBox` with values: "Minor", "Major", "Blocker".
6. Create a JTextField called `projectNameField`.
7. Create a ModernButton called `attachBtn` with text "Attach Screenshot".
8. Create a JLabel called `fileLabel` that shows "No file selected".

### Step 7: Add Attachment Logic

**Purpose:** To allow attaching an image file to the bug report.

1. Add an ActionListener to attachBtn.
2. Inside, create a JFileChooser.
3. Set the file chooser to only accept image files (JPG, PNG).
4. Show the open dialog and check if user clicked OK.
5. If yes, get the selected file and store it in `selectedFile`.
6. Set fileLabel's text to the file's name.

### Step 8: Add Submit Button Logic

**Purpose:** To validate and save the new bug report.

1. Create a ModernButton called `submitBtn` with text "Submit Bug".
2. Add all labels and fields to the dialog in the correct order.
3. Add an ActionListener to submitBtn.
4. Inside, validate that titleField is not empty. If empty, show error message and return.
5. Get values from all fields.
6. Get current date/time and convert to String format (you can use `new Date().toString()` or format it nicely).
7. Generate a unique ID for the bug. You can do this by calling `bugService.getAllBugs()`, getting the size, and adding 1. Or use `System.currentTimeMillis()` to get a unique number.
8. Create a new Bug object with:
   - ID from step 7
   - Title from titleField
   - Description from descArea
   - Bug type, priority, bug level from combo boxes
   - Project name from projectNameField
   - Bug date as current date string
   - Status as OPEN (BugStatus.OPEN)
   - AssigneeId as 0 (will be assigned later)
   - ReporterId as tester's ID
   - Screenshot path as empty string initially
9. If selectedFile is not null, call `bugService.saveScreenshot` with selectedFile and the bug's ID, and store the returned path.
10. If screenshot path was returned, set it on the bug object using `bug.setScreenshotPath()`.
11. Call `bugService.createBug` with the new bug.
12. Show a message "Bug reported successfully!".
13. Call `dispose()` to close the dialog.

---

## Phase 3: Testing Your Work

1. Run the application and login as a Tester.
2. Click "Report New Bug" button.
3. Fill in all the fields in the dialog.
4. Try attaching a screenshot image file.
5. Click "Submit Bug".
6. Verify the bug appears in the table.
7. Check the data/bugs.json file to see if the bug was saved.
8. Check the data/screenshots folder to see if the image file was copied there.
