# TODO: Person 6 (Michael) - Data & Infrastructure Engineer

**Role:** Backend & Data Specialist  
**Focus:** Saving Data to Files, Managing Repositories, and Fake Emails.

## Before You Start

- You will be working with standard Java I/O (`java.io`).
- We will store data in text files (`.txt`) using a pipe (`|`) delimiter.
- No external libraries are required.

---

## Phase 1: Create the Repository Interface

**Goal:** Define the rules (interface) that all repositories must follow.

### Step 1: Create Repository Interface

1. Open the `repository` package.
2. Open the `Repository` interface (it should already exist).
3. Add imports: `java.util.List`.
4. Define method: `List<T> findAll();`
5. Define method: `T findById(int id);`
6. Define method: `void save(T entity);`
7. Define method: `void delete(int id);`

### Step 2: Open FileRepository Class

**Purpose:** To implement the generic logic for saving and loading data from JSON files.

1. Open the `FileRepository` class (it should already exist).
2. Add imports: `java.io.*`, `java.util.*`.

### Step 3: Add Class Variables

**Purpose:** To store the necessary components for file operations and JSON serialization.

1. Create a protected variable called `filename` of type `String`.

### Step 4: Create Constructor

**Purpose:** To initialize the repository with the file path and the generic type for Gson.

1. Create a public constructor that takes `String filename` as a parameter.
2. Store the filename in `this.filename`.
3. Call `createFileIfMissing()` (you will implement this next).

### Step 5: Implement findAll Method

**Purpose:** To read all lines from the text file.

1. Implement a protected method `readLines()` returning `List<String>`.
2. Use `BufferedReader` with `FileReader` to read the file line by line.
3. Add each line to a `List<String>`.
4. Handle IOExceptions.

**Also, define abstract methods:**
1. `public abstract List<T> findAll();`
2. `public abstract void save(T entity);`
3. `public abstract void updateAll(List<T> entities);`

### Step 6: Implement findById Method

**Purpose:** To find a specific object by its unique ID.

1. Implement the `findById` method to return `T`.
2. Call `findAll()` and store in a variable called `items`.
3. Loop through `items`.
4. Inside loop, get the ID of the item by calling `getId()` method on it. Note: All entities (User, Bug) have a `getId()` method that returns an int, so you can safely call it.
5. Compare the item's ID with the `id` parameter. If they match, return the item.
6. After the loop, return null (not found).

### Step 7: Implement save Method

**Purpose:** To write a list of strings to the file.

1. Implement a protected method `writeLines(List<String> lines)`.
2. Use `BufferedWriter` with `FileWriter` (overwrite mode).
3. Iterate through strings and write them, followed by `newLine()`.

### Step 8: Implement delete Method

**Purpose:** To remove an object from the file by its ID.

1. Implement the `delete` method that takes `int id` and returns void.
2. Call `findAll()` and store in `allItems`.
3. Create a variable `itemToRemove` of type T, set to null.
4. Loop through `allItems`.
5. Inside loop, check if current item's ID (call `getId()`) equals the `id` parameter.
6. If yes, store the item in `itemToRemove` and break the loop.
7. After the loop, if `itemToRemove` is not null, remove it from `allItems` using `allItems.remove(itemToRemove)`.
8. Wrap the next steps in try-catch (IOException).
9. Inside try, create `FileWriter writer = new FileWriter(file)`.
10. Call `gson.toJson(allItems, writer)` to save the updated list back to file.
11. Close the writer (or use try-with-resources).

### Step 9: Open UserRepository Class

**Purpose:** To handle data persistence specifically for User objects.

1. In the `repository` package, open the `UserRepository` class.
2. Extend `FileRepository<User>`.
3. Implement `findAll()`:
   - Call `readLines()`.
   - Convert each line to a User object using a helper method `fromFileString`.
4. Implement `save(User user)`:
   - Add user to list, then call `updateAll`.
5. Implement `toFileString(User u)`:
   - Return string: `id|username|password|role`.
6. Implement `fromFileString(String line)`:
   - Split by pipe `|` and create User object.

### Step 10: Open BugRepository Class

**Purpose:** Similar to User, handle `Bug` persistence.

1. Extend `FileRepository<Bug>`.
2. Implement `toFileString(Bug b)`:
   - Return string: `id|title|desc|...|bugDate|...`.
3. Implement `fromFileString(String line)`:
   - Parse the pipe-separated line.

---

## Phase 2: Build Email Service & Data Seeder

**Goal:** Handle notifications and initial data setup.

### Step 1: Open EmailService Class

**Purpose:** To simulate sending email notifications by logging them to a file.

1. Open `service` package.
2. Open the `EmailService` class (it should already exist).
3. Add imports: `java.io.*`, `java.util.Date`.

### Step 2: Create sendEmail Method

**Purpose:** To write the email details to the log file.

1. Create `public void sendEmail(String to, String subject, String body)`.
2. Create String `logMessage` with Date, To, Subject, Body.
3. Wrap in try-catch (IOException).
4. Create `FileWriter writer = new FileWriter("data/email_logs.txt", true)` (true for append).
5. Write `logMessage` + newline.
6. Close writer.

### Step 3: Create DataSeeder Class

**Purpose:** To populate the system with initial data if it's empty.

1. Open `util` package.
2. Create class `DataSeeder` (this one likely does NOT exist yet, so create it).
3. Add imports: `com.bugtracker.repository.UserRepository`, `com.bugtracker.entity.User`, and `com.bugtracker.entity.User.UserRole`.

### Step 4: Create seedUsers Method

**Purpose:** To ensure there is at least one Admin user to start with.

1. Create `public static void seedUsers()`.
2. Create `UserRepository repo = new UserRepository()`.
3. If `repo.findAll().isEmpty()`:
4. Create `User admin = new User(1, "admin", "admin123", User.UserRole.ADMIN)`.
5. Call `repo.save(admin)`.
6. Print "Admin user seeded."

---

## Testing Your Work

1. Create a simple test Main class in your package.
2. Create a UserRepository and try saving a test user.
3. Check if "data/users.json" file is created.
4. Open the JSON file and verify the user data is there.
5. Try calling findAll and verify it reads the data back.
6. Try the same tests with BugRepository.
