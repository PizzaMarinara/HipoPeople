# HipoPeople

## General Architecture notes
- As per request, the HipoPeople app is built with an MVVM architecture, fully in Kotlin.
- The app uses Hilt as a Dependency Injection library, which has its own package (named "di") in the root app folder.
- For UI, I used the newest Jetpack Compose libraries.
- The buildSrc submodule is used to organize all the library dependencies in a single point.

## Packaging structure
- The packaging structure I used is mostly built upon Google's best practices described [here](https://developer.android.com/topic/architecture/).
- In the shared folder, I keep files for Constants, Extension functions and a specific class called [Resource](app/src/main/java/dev/efantini/hipopeople/shared/Resource.kt) that is used for asynchronous loadings.
- In the presentation layer, the packaging is divided by screen first, instead of by type first, as I think it's more clear and well organized. This is just a personal preference as I rather have all the files I'm working on in the same package rather than having to open the "viewmodels" subpackage to get the viewmodel, the "states" subpackage to get the state etc.

## Data layer
- The models in the dto package are built using the API docs from the specifications, they're used to model the JSON elements returned by the API.
- This layer is the one that makes use of the Resource class mentioned above: its purpose, used with asynchronous flows, is to better encapsulate the behavior of an asynchronous resource load, with the states of Loading, Success and Error.
- I assumed the uniqueness of the Github username and, as such, it's the primary key for the Member data class, and the insert function is set to replace (update) a Member if you add a member with the same Github username.
- There isn't much else to say about this layer other than the very standard use of Retrofit for remote requests and Room for local database handling.


## Domain layer
- I wanted to make a specific note about use cases in the domain layer. I feel like, if this was a "real" app as it is right now, those would be very much superfluous since they don't do much else other than calling a repository. Of course, since this is a test task, I wanted to include them just to show how I'd build a hypothetically bigger app, where use cases would encapsulate more complex behavior, with the purpose of not bloating the viewmodels.
- The repository interfaces are instead a case where, no matter how simple, I'd rather have an interface behavior defined in the domain layer and its implementations (remote, local or whatever) in the data layer. This is done to keep the layers very much separated and the codebase clean.

## Presentation layer
- As I've said, the UI is fully done in Compose, using the navigation component to switch between the screen contents.
- If we assume that network work is fully delegated to the Retrofit library, and local CRUD operations are delegated to the Room library, technically the only real "logic"
- Technically, the only real "logic" of the app (if we assume that network work is fully delegated to Retrofit and local CRUD operations are delegated ) is the filtering that is done in the members list screen which is strictly a view related logic (no reason to query the domain and reload the list at each text change).
- If we assume that network work is fully delegated to the Retrofit library, and local CRUD operations are delegated to the Room library, technically the only real "logic" of the app is the members list filtering, which is strictly a view related logic.
- There is no real need, given the current specifications, to re-query the database through the domain and data layers, so the search function is just implemented in the UI layer by filtering the full list of elements at each key.

## Finishing notes/assumptions
- Since this is just a test task, I didn't want to include any functionality that wasn't specifically requested in the document, but I figured I'd add a couple ideas I had about those in here.
- I would implement a validation check for the add member form. Right now, the user is only suggested to complete the fields in a meaningful way (eg. numeric keyboard for age and years in Hipo) but there's no real check, for example, if the Github username exists. The only thing that is checked is if every field is completed for basic data integrity.
- The filtering in the members list view now searches only the name, position and github username (done in the .filter extension function) but of course that could be subject to change depending on what fields you would like to filter.
- In the [Extensions](app/src/main/java/dev/efantini/hipopeople/shared/Extensions.kt) file I've defined extension functions even for custom classes: you could technically write these function directly in the class body but I decided to keep them all in a single file to leave the Model as clean as possible.
- I've used a personal theme template defined in the theme subpackage, which implements Light/Dark palettes automatically on the newest Android systems. Most of it might be quite superfluous, but it's there as a placeholder as it only concerns purely the graphical aspect.
