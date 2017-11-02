# Name: Allocate Task

## Actors
* User

## Goals
Users use this use case to allocate tasks.

## Preconditions:
* An unallocated task must exist.
* A user must either be a parent or be logged into the account they wish to assign the task to.

## Summary
This use case is for users to allocate unallocated tasks. Any task that is either new or has been released by another user is considered unallocated.

## Steps
| Actor Action | System Responses |
| --- | --- |
| Choose a task | (If user is a parent) Prompt user for account to allocate task to (otherwise skip to next system response) |
| Select a user to allocate to | Prompt user for confirmation
| Confirm allocation | confirm to user action has been completed |

## Post-conditions
* The unallocated task is now allocated.
* The formally unallocated task is now allocated to the user.