# GitHub Workflow Guide

Use this guide when working on the phased implementation plan.

## Core Rules

- Never work directly on `main`.
- Keep branch names tied to the phase or feature, not a person.
- Test your changes before opening a pull request.

## 1. Clone The Repository

```bash
git clone repo-link
```

Open the project in NetBeans or your preferred Java IDE after cloning.

## 2. Create A Branch

Use a branch name that describes the work clearly.

Examples:

- `phase-1-project-baseline`
- `phase-3-auth-routing`
- `feature/admin-user-crud`
- `fix/status-handling`

Create the branch:

```bash
git checkout -b phase-3-auth-routing
```

Push it to GitHub:

```bash
git push -u origin phase-3-auth-routing
```

## 3. Daily Work Cycle

Stage changes:

```bash
git add .
```

Commit with a clear message:

```bash
git commit -m "Update login routing for manager dashboard"
```

Push updates:

```bash
git push
```

## 4. Stay Up To Date

Before starting new work or opening a PR, sync with `main`.

```bash
git checkout main
git pull
git checkout phase-3-auth-routing
git merge main
```

## 5. Open A Pull Request

When the phase task is ready:

1. Push your latest branch changes.
2. Open GitHub.
3. Create a pull request from your branch into `main`.
4. Title the PR with the phase or feature being completed.
5. Summarize what changed, what was tested, and any known gaps.

## 6. Handle Merge Conflicts

If Git reports a conflict:

1. Open the conflicted file.
2. Remove the conflict markers.
3. Keep the correct final code.
4. Stage the resolved file.
5. Commit the merge resolution.

Example:

```bash
git add .
git commit -m "Resolve merge conflict in bug workflow"
```

## 7. Planning Alignment

Before starting a branch, check which phase document applies:

1. `PHASE_1_PROJECT_BASELINE.md`
2. `PHASE_2_DATA_AND_DOMAIN.md`
3. `PHASE_3_AUTH_AND_NAVIGATION.md`
4. `PHASE_4_ADMIN_AND_MANAGER_WORKFLOWS.md`
5. `PHASE_5_TESTER_AND_DEVELOPER_WORKFLOWS.md`
6. `PHASE_6_QA_AND_RELEASE_READINESS.md`

## Final Checklist

- [ ] Work was done on a branch.
- [ ] Branch name matches the phase or feature.
- [ ] Changes were tested locally.
- [ ] Pull request targets `main`.
- [ ] The PR summary references the correct implementation phase.
