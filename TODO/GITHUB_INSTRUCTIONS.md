# 🐙 Practical GitHub Guide for the Team

**Goal:** Follow these exact steps to work together without breaking the code.
**Rule #1:** NEVER work directly on the `main` branch.

---

## 1️⃣ Start: Get the Code
**Why:** You need the project files on your computer to start working.

1.  Open your terminal/command prompt.
2.  Run this command:
    ```bash
    git clone repo-link
    ```
3.  Open the folder in your IDE.

---

## 2️⃣ The Golden Rule: PROTECT MAIN
**Why:** `main` is the "Official Working Version". If you push broken code to `main`, everyone's project breaks.

*   **You** must work in your own safe area (a "Branch").

---

## 3️⃣ Create Your Workspace (Branch)
**Why:** A branch is your personal copy. You can break things here, and it won't affect anyone else.

1.  Create and switch to your branch:
    ```bash
    git checkout -b personX
    ```
    *(Replace `personX` with your name, `person2-admin` for example)*

2.  Save this branch to GitHub:
    ```bash
    git push origin personX
    ```

---

## 4️⃣ Daily Work Cycle
**Why:** You need to save your work to the cloud so it's safe and others can see it.

**Step 1: Save changes locally**
After you write some code, run:
```bash
git add .
```

**Step 2: Commit (Label your save)**
Tell us what you did:
```bash
git commit -m "Added login button styling"
```

**Step 3: Push (Send to Cloud)**
Upload your save to GitHub:
```bash
git push origin personX
```

---

## 5️⃣ Finish a Task: Create a Pull Request (PR)
**Why:** You are done. You want Person 1 put it into the official `main` version.

1.  Go to the GitHub website.
2.  Click **"Pull Requests"** tab -> **"New Pull Request"**.
3.  **Settings:**
    *   **Base:** `main` (Where your code goes)
    *   **Compare:** `personX` (Where your code is)
4.  Click **"Create Pull Request"**.
5.  Wait for Person 1 to review and merge it.

---

## 6️⃣ Keep Up to Date (Sync with Main)
**Why:** While you were working, someone else might have finished their task. You MUST get their changes into your branch to avoid errors later.

1.  Switch to main:
    ```bash
    git checkout main
    ```
2.  Download updates:
    ```bash
    git pull
    ```
3.  Go back to your branch:
    ```bash
    git checkout personX
    ```
4.  Combine updates into your branch:
    ```bash
    git merge main
    ```

---

## 7️⃣ What if there is a Conflict? 😱
**Why:** Two people changed the exact same line of code. Git doesn't know which one to keep.

1.  Open the file with the conflict (it will have `<<<<<<<` and `>>>>>>>` markers).
2.  **Manually delete** the wrong code and the markers. Keep only the correct code.
3.  Save the file.
4.  Tell Git you fixed it:
    ```bash
    git add .
    git commit -m "Fixed merge conflict"
    ```

---

## 🚀 Summary Checklist
1.  **Clone** repo (First time only).
2.  **Checkout** your branch (`git checkout -b personX`).
3.  **Work** on your task.
4.  **Add & Commit** often (`git add .`, `git commit`).
5.  **Pull** updates from main (`git pull` -> `git merge`).
6.  **Push** to GitHub (`git push`).
7.  **Create PR** when finished.

**⚠️ REMINDERS:**
*   NEVER work inside another person's folder.
*   NEVER change JSON files manually (let the code do it).
*   ALWAYS test your part before making a PR.
