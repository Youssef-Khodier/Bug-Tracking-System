# TODO: Person 6 (Michael) - Data & Infrastructure Engineer

**Role:** Backend & Data Specialist  
**Focus:** Saving Data to Files, Managing Repositories, and Fake Emails.

## Before You Start

- You need the Gson library JAR file. Download `gson-2.10.1.jar` and put it in the `lib/` folder.
- Make sure you add the JAR file to your IDE's build path/classpath.
- Understand that Gson converts Java objects to JSON text format so we can save them to files.

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
2. Add imports: `com.google.gson.Gson`, `com.google.gson.GsonBuilder`, `com.google.gson.reflect.TypeToken`, `java.io.*`, `java.lang.reflect.Type`, `java.util.*`.

### Step 3: Add Class Variables

**Purpose:** To store the necessary components for file operations and JSON serialization.

1. Create a protected variable called `gson` of type `Gson`.
2. Create a protected variable called `file` of type `File`.
3. Create a protected variable called `type` of type `Type`.

### Step 4: Create Constructor

**Purpose:** To initialize the repository with the file path and the generic type for Gson.

1. Create a public constructor that takes `String filePath` and `Type type` as parameters.
2. Inside, create a new File using filePath and store it in `file`.
3. Store the type parameter in `type`.
4. Create a GsonBuilder, call `setPrettyPrinting()` on it, then call `create()` and store in `gson`.
5. Check if the file's parent directory doesn't exist. If true, call `mkdirs()` to create it.

### Step 5: Implement findAll Method

**Purpose:** To read all objects from the JSON file.

1. Implement the `findAll` method to return `List<T>`.
2. Check if the file doesn't exist. If true, return a new empty ArrayList.
3. Wrap in try-catch (IOException).
4. Inside try, create `FileReader reader = new FileReader(file)`.
5. Call `gson.fromJson(reader, type)` and store in `List<T> items`.
6. If items is null, return a new empty ArrayList.
7. Return `items`.
8. In the catch block, print the error and return a new empty ArrayList.

### Step 6: Implement findById Method

**Purpose:** To find a specific object by its unique ID.

1. Implement the `findById` method to return `T`.
2. Call `findAll()` and store in a variable called `items`.
3. Loop through `items`.
4. Inside loop, get the ID of the item by calling `getId()` method on it. Note: All entities (User, Bug) have a `getId()` method that returns an int, so you can safely call it.
5. Compare the item's ID with the `id` parameter. If they match, return the item.
6. After the loop, return null (not found).

### Step 7: Implement save Method

**Purpose:** To add a new object or update an existing one in the file.

1. Implement the `save` method that takes `T item` and returns void.
2. Call `findAll()` and store in `allItems`.
3. Get ID of `item` by calling `item.getId()` (all entities have this method).
4. Create a boolean variable `found` set to false.
5. Create an int variable `index` set to -1.
6. Loop through `allItems` with index (use a for loop with counter).
7. Inside loop, check if current item's ID (call `getId()`) equals the new item's ID.
8. If yes, set `found` to true, store the index, and break the loop.
9. After the loop, if `found` is true, replace the item at that index with the new item.
10. If `found` is false, add the new `item` to `allItems`.
11. Wrap in try-catch (IOException).
12. Inside try, create `FileWriter writer = new FileWriter(file)` (or use try-with-resources).
13. Call `gson.toJson(allItems, writer)`.
14. Close the writer (or it will auto-close with try-with-resources).

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

1. In the `repository` package, open the `UserRepository` class (it should already exist).
2. Make sure it extends `FileRepository<User>`.
3. Add import for `com.bugtracker.entity.User` and `com.google.gson.reflect.TypeToken`.
4. Create a constructor.
5. Inside, call `super` with "data/users.json" and `new TypeToken<List<User>>(){}.getType()`.

### Step 10: Open BugRepository Class

**Purpose:** To handle data persistence specifically for Bug objects.

1. In the `repository` package, open the `BugRepository` class (it should already exist).
2. Make sure it extends `FileRepository<Bug>`.
3. Add import for `com.bugtracker.entity.Bug` and `com.google.gson.reflect.TypeToken`.
4. Create a constructor.
5. Inside, call `super` with "data/bugs.json" and `new TypeToken<List<Bug>>(){}.getType()`.

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
