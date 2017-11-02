# Name: Allocate Task to Self

## Actors
* User

## Goals
Users use this use case to allocate task to themselves.

## Preconditions:
* An unallocated task must exist.
* A user must be signed into the account they wish to allocate the task to.

## Summary
This use case is for users to take on unallocated tasks. Any task that is either new or has been released by another user is considered unallocated.

## Related Use Cases
Specialization of: Allocate Task

## Steps
| Actor Action | System Responses |
| --- | --- |
| Choose a task | Confirmation dialog appears |
| Confirm allocation | Confirmation dialog disappears |

## Post-conditions
* The unallocated task is now allocated.
* The formally unallocated task is now allocated to the user.