# Phase 2: Data And Domain

## Goal

Make the models, file formats, and persistence layer consistent across the whole application.

## Scope

- `bugtrackingsystem.model.User`
- `bugtrackingsystem.model.Bug`
- `bugtrackingsystem.model.Project`
- `bugtrackingsystem.model.Comment`
- `bugtrackingsystem.service.Repository`
- `bugtrackingsystem.service.UserRepository`
- `bugtrackingsystem.service.BugRepository`
- `bugtrackingsystem.service.FileRepository`
- `bugtrackingsystem.service.ProjectService`

## Main Tasks

1. Confirm the file contracts used by `data/users.txt`, `data/bugs.txt`, `data/projects.txt`, and `data/email.txt`.
2. Keep `User`, `Bug`, and `Project` serialization aligned with repository parsing logic.
3. Verify bug fields such as `title`, `description`, `status`, `priority`, `reporterId`, `assigneeId`, `projectId`, `type`, `level`, `date`, and `screenshotPath` are handled consistently.
4. Standardize bug status values used in services and dashboards so reporting and workflow logic do not disagree.
5. Confirm project records are available for bug creation, assignment, and manager views.
6. Keep file creation behavior inside the persistence layer so the app can start from an empty `data/` directory.

## Deliverables

- Stable text-file persistence contracts
- Consistent domain models across UI and services
- A documented bug status vocabulary for the rest of the project

## Completion Checklist

- [ ] Text-file formats are documented and stable.
- [ ] Repositories and models agree on field order.
- [ ] Status names are consistent across the application.
- [ ] Project data can be loaded and saved.
- [ ] Persistence works without a database.
