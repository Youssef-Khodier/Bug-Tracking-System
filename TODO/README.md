# 🐞 Bug Tracking System: Project Overview

## 📖 What is this project?
This is a **Desktop Application** built in Java to help software teams track and manage bugs (defects) in their projects.

## 🎯 The Goal
To build a fully functional **Bug Tracker** using **Object-Oriented Programming (OOP)** principles.
We are NOT using a database server (like MySQL). Instead, we are using **JSON files** to save our data, which makes the app portable and easy to run.

---

## 🔑 Key Features

### 1. User Roles (Who uses the app?)
*   **Admin:** The boss. Can create new users and delete them.
*   **Project Manager (PM):** The overseer. Can view reports and see how many bugs are open.
*   **Developer:** The worker. Can view bugs assigned to them and change status to "Fixed".
*   **Tester:** The finder. Can report new bugs and attach screenshots.

### 2. The Bug Lifecycle
A bug goes through a specific flow:
1.  **OPEN:** Tester reports it.
2.  **IN_PROGRESS:** Developer starts working on it.
3.  **COMPLETED:** Developer fixes it.

### 3. Dashboards
Each user sees a different screen (Dashboard) customized for their job.
*   *Example:* The Admin sees a list of users, while the Developer sees a list of bugs.

---

## 🛠️ Technology Stack (What are we using?)
*   **Language:** Java (JDK 17+)
*   **GUI (Graphics):** Swing (Standard Java UI library)
*   **Styling:** Custom "Dark Mode" engine (No external UI libraries)
*   **Data Storage:** JSON Files (using Google Gson library)
*   **Build Tool:** **NONE.** (Manual JAR management)

---

## 📂 Project Structure
*   `src/com/bugtracker/ui`: All the visual screens (Dashboards, Login).
*   `src/com/bugtracker/entity`: The data objects (User, Bug).
*   `src/com/bugtracker/repository`: The code that saves/loads files.
*   `src/com/bugtracker/service`: The business logic (Rules).
*   `data/`: Where the JSON files live.
*   `lib/`: Where the Gson JAR lives.
