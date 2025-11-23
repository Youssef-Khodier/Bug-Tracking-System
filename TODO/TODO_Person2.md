# TODO: Person 2 (Fityan) - Admin Module Developer

**Role:** Admin Module Specialist  
**Focus:** Managing Users, Assigning Roles, and the Admin Screen.

## Before You Start

- Look at the `User` class in the `entity` package. It has `id`, `username`, `password`, and `role` fields.
- You MUST use `StyleUtils` and `ModernButton` from Person 1. Never use standard Swing colors.
- Person 6 creates the Repository classes. You will use `UserRepository` to save data.

---

## Phase 1: Create User Management Logic

**Goal:** Build the service class that handles adding, editing, and deleting users.

### Step 1: Open UserService Class

**Purpose:** To set up the class that will handle all user-related operations.

1. Open the `service` package in your IDE.
2. Open the `UserService` class (it should already exist).
3. At the top, add imports: `java.util.List` and `com.bugtracker.entity.User`.
4. Inside the class, create a private variable called `userRepository` of type `UserRepository`.
5. Create a public constructor with no parameters.
6. Inside the constructor, set `userRepository` to a new `UserRepository()` object.

### Step 2: Create getAllUsers Method

**Purpose:** To retrieve the list of all users from the database/file.

1. Create a public method called `getAllUsers` that returns `List<User>`.
2. Inside this method, call `userRepository.findAll()` and return the result.

### Step 3: Create addUser Method

**Purpose:** To add a new user to the system after validating the input.

1. Create a public method called `addUser` that takes one parameter: `User newUser` and returns nothing (void).
2. Inside the method, create an if statement checking if `newUser.getUsername()` is null OR empty.
3. If true, print "Error: Username required" to console and return from the method.
4. After the if statement, call `userRepository.save(newUser)`.

### Step 4: Create updateUser Method

**Purpose:** To save changes made to an existing user's profile.

1. Create a public method called `updateUser` that takes one parameter: `User existingUser` and returns void.
2. Inside this method, call `userRepository.save(existingUser)`.

### Step 5: Create deleteUser Method

**Purpose:** To remove a user from the system permanently.

1. Create a public method called `deleteUser` that takes one parameter: `int userId` and returns void.
2. Inside this method, call `userRepository.delete(userId)`.

---

## Phase 2: Build Admin Dashboard Screen

**Goal:** Create the visual window where the Admin manages users.

### Step 1: Open AdminDashboard Class

**Purpose:** To set up the main window for the admin interface.

1. Open the `ui.dashboard` package.
2. Open the `AdminDashboard` class (it should already exist).
3. Make sure it extends `JFrame`.
4. Add imports: `javax.swing.*`, `java.awt.*`, and `com.bugtracker.ui.components.ModernButton`.
5. Create a private variable called `userService` of type `UserService`.
6. Create a private variable called `tableModel` of type `DefaultTableModel`.

### Step 2: Build the Constructor

**Purpose:** To initialize the dashboard window properties and layout.

1. Create a public constructor with no parameters.
2. Inside, call `setTitle` with the text "Admin Dashboard".
3. Call `setSize` with width 800 and height 600.
4. Call `setDefaultCloseOperation` with `JFrame.EXIT_ON_CLOSE`.
5. Call `setLayout` with `new BorderLayout()`.

### Step 3: Create Top Header Panel

**Purpose:** To create a visual header for the dashboard.

1. Create a variable called `topPanel` of type `JPanel`.
2. Set its background color to `StyleUtils.COLOR_BACKGROUND`.
3. Create a variable called `titleLabel` of type `JLabel` with text "Admin Dashboard".
4. Set the font of `titleLabel` to `StyleUtils.FONT_BOLD`.
5. Add `titleLabel` to `topPanel`.
6. Add `topPanel` to the frame using `add(topPanel, BorderLayout.NORTH)`.

### Step 4: Create Side Button Panel

**Purpose:** To provide navigation buttons for admin actions.

1. Create a variable called `sidePanel` of type `JPanel`.
2. Set its layout to `new GridLayout` with 5 rows, 1 column, and gaps of 10 pixels.
3. Create a variable called `manageUsersBtn` of type `ModernButton` with text "Manage Users".
4. Create a variable called `logoutBtn` of type `ModernButton` with text "Logout".
5. Add both buttons to `sidePanel`.
6. Add `sidePanel` to the frame using `add(sidePanel, BorderLayout.WEST)`.

### Step 5: Create User Table

**Purpose:** To display the list of users in a tabular format.

1. Create a String array called `columnNames` with three values: "ID", "Username", "Role".
2. Create `tableModel` by calling `new DefaultTableModel` with `columnNames` and 0 rows.
3. Create a variable called `userTable` of type `JTable` using `tableModel`.
4. Create a variable called `scrollPane` of type `JScrollPane` wrapping `userTable`.
5. Add `scrollPane` to the frame center using `add(scrollPane, BorderLayout.CENTER)`.

### Step 6: Create loadUsers Method

**Purpose:** To fetch user data and populate the table.

1. Create a private method called `loadUsers` that returns void.
2. Inside, call `tableModel.setRowCount(0)` to clear the table.
3. Call `userService.getAllUsers()` and store the result in a variable called `users`.
4. Create a for loop that goes through each user in `users`.
5. Inside the loop, create an Object array with three elements: user's ID, user's username, and user's role.
6. Call `tableModel.addRow` with that Object array.

### Step 7: Open UserDialog Class

**Purpose:** To create a popup form for adding or editing users.

1. In the `ui.dialog` package, open the `UserDialog` class (it should already exist).
2. Make sure it extends `JDialog`.
3. Create a constructor that takes `JFrame parent` and `UserService service` as parameters.
4. Call `setTitle` with "Add/Edit User".
5. Call `setModal` with true.
6. Call `setLayout` with `new GridLayout` of 5 rows, 2 columns, gaps 10.
7. Create a `JTextField` called `usernameField`.
8. Create a `JPasswordField` called `passwordField`.
9. Create a `JComboBox<String>` called `roleBox` with values: "ADMIN", "PROJECT_MANAGER", "DEVELOPER", "TESTER".
10. Create a `ModernButton` called `saveButton` with text "Save".
11. Create a `ModernButton` called `cancelButton` with text "Cancel".
12. Add all labels and fields to the dialog in order.
13. For the cancel button, add an ActionListener that calls `dispose()`.
14. For the save button, add an ActionListener that gets values from fields, creates a new User, calls `service.addUser`, and then `dispose()`.

---

## Phase 3: Connect Everything Together

1. In the AdminDashboard constructor, initialize `userService` to a new `UserService()`.
2. At the end of the constructor, call `loadUsers()`.
3. Add an ActionListener to `manageUsersBtn` that opens a new `UserDialog`.
4. Add an ActionListener to `logoutBtn` that closes the dashboard.

---

## Testing Your Work

1. Run the application and login as Admin (ask Person 1 how to do this).
2. Click "Manage Users" button.
3. Try adding a new user with username "testuser".
4. Check if the user appears in the table.
5. Close and reopen the app. Verify the user is still there in the data file.
