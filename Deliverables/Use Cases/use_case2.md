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
|  | If no accounts exist, prompt user to set up app |
| Choose "Create Account" from settings menu | Screen changes to display input fields for new account |
| User enters Name and chooses privilege level (Adult/Child) |  |
| User confirms information by tapping "Okay" button at bottom of screen | Toast informing the user the account has been created |
| | Screen changes back to default task list |

## Post-conditions
* The account will now be available to choose when assigning tasks
* The account will now appear on the switch user screen
* The user will now be on the main task list screen.