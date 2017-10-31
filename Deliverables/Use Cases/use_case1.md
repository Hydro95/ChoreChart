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
Specialization of: Allocate Task //Do we need hierchies?

## Steps
### Actor Action				System Responses
1. Choose a task 				Confirmation dialog appears
1. Confirm allocation			Confirmation dialog dissapears

## Postconditions
* The unallocated task is now allocated.
* The the formally unallocated task is now allocated to the user.