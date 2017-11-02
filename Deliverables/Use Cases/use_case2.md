# Name: Create New Account

## Actors
* Adult

## Goals
Create a new Adult or Child account

## Preconditions:
* If an Adult account exists in the app, the user must be logged into it.
* If no accounts exist, the user will be prompted to create a new Adult account.

## Summary
This use case allows an Adult to create an account for either another or a child. The adult has elevated privileges that allow them to do so. This ensures control of the app is left in the hands of the adult.

## Related Use Cases
Set up app (If no accounts exist)

## Steps
| Actor Action | System Responses |
| --- | --- |
| opens app for first time | If no accounts exist, prompt user to set up app |
| Choose "Create Account" from settings menu | Prompts user for new account information |
| User enters Name and chooses privilege level (Adult/Child) |  |
| User confirms information | Confirm to user account has been created |

## Post-conditions
* The account will now be available to choose when assigning tasks
* The account will now appear in the list of users.