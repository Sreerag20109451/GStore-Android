# CA - Assignment - Mobile Application Development

This project is an attempt at an e-commerce platform using Android, Kotlin, and Compose.

### Personal Statement

This is to certify that the idea, logic, and execution of this project are mine. The inspirations for the code are given in references/


## Functionalities

### Authentication
Authentication has been implemented in this projects by using
- Custom Signup/Login
- Google Signup

### Main Screen
The main screen consists of 4 screens, including
- User Profile screen for displaying user Data
- Category Screen for displaying products by categories
- Fitering by Categories and Price, search by name or categories, sort by price.
- Orders Screen

### Cart and  Orders
This project has implemented a demo of carts and orders.
- Carts for storing products to be ordered
- Orders for displaying order history

### Other functionalities
- Dark Mode
- Gesture for deleting a cart item


## UX/DX

JetPack compose was used for developing the application. 

## Architecture

MVVM Architecture has been used for developing the project along with Hilt for Dependency Injection. DAOs, Interfaces, Models, ViewModels  and Repositories were used extensively.



## 3rd Party Integrations
- Firestore - Firestore has been used for database purposes
-  FirebaseAuth - Firebase Authentication with Google Auth has been used for user authentication
-  Firebase Storage - Used for storing Images
-  Coli - Has been used for image rendering

## Github workflow
Frequently committed with the usage of the develop branch(mostly the feature name), the release branch, and tagged releases.

MAIN -> DEVELOP BRANCH -> MAIN -> RELEASE BRANCH -> RELEASE


## UML  Diagram - A rough Sketch

[uml](https://github.com/Sreerag20109451/GStore-Android/blob/main/UML-Rotated.jpg)




## References

[firebase-auth](https://cloud.google.com/appengine/docs/standard/python3/building-app/authenticating-users)
[gestures](https://m3.material.io/foundations/interaction/gestures)
[hilt](https://developer.android.com/training/dependency-injection/hilt-android)








