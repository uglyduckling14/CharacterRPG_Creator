# Assignment 4:
## Tabletop RPG Character Sheet

### Requirements:
Login Screen:
    On launch, user should be asked to log in/create account
    If user is still logged in, user should be taken to home screen
        Use Todos example code

Home Screen:
    User should have list of created characters
        - Display character's name, race and class and edit button
    User should see button where they can create a character
        -Should be saved to Firebase
        - Navigate to new screen
    Log out requirement

Character Screen:
    Form where user can give information for character.
    On save, transition user back to home screen

Edit Screen:
    Pre-populated form with character's data
    Should update to todo in Firebase

Character Object:
    Name: string
    Age: Int
    Race: string
    class: string
    height: int
    gender: string
    description: string
