# Use Case 3: Create A Communal Shopping List

## Actors
* Adult

## Goals
Create a new communal shopping list and associate it with a Home.

## Preconditions:
* The user must be logged in as an adult.
* A home must have been created prior.

## Summary
Create a shopping list for a Home.

## Related Use Cases
Add an item to the shopping list.
Edit an item on the shopping list.
Remove an item on the shopping list.

## Steps
| Actor Action | System Responses |
| --- | --- |
| Select "Create a shopping list" | Open a dialog prompting for shopping list name, Home it is associated with, and (optionally) list of items |
| User enters shopping list name, home it is associated with, and (optionally) a list of items |  |
| User confirms information | Save the shopping list |
| | Confirm to user that the shopping list is saved. |

## Post-conditions
* The shopping list will now be available on a list of shopping lists for a given Home.
* The shopping list can be modified by of the members of the Home.
