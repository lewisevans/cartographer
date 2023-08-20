Cartographer
=====

Cartographer is an annotation processor library using Kotlin Symbol Processing (KSP) for type-safe
Android Jetpack Compose Navigation Graph building.
It removes the need to write all the boilerplate yourself and is designed to be simple and intuitive.

_Note: This is pre-alpha and only the start of the journey._

Annotate your `@Composable` with `@Destination`
```kotlin
@Destination
@Composable
fun ScreenBlue() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}
```
Cartographer will generate an extension on `NavGraphBuilder` that it will add to an auto generated
`NavHost` currently called `AppNavHost`. We will be adding the ability to create multiple `NavHost`s
in the near future

Cartographer will generate the typesafe code for you to navigate to the screen via another extension
on the `NavController` that the AppNavHost is passed
```kotlin
navController.navigateToScreenBlue()
```

You can add arguments to the composable `@Destination` annotated function
```kotlin
@Destination
@Composable
fun ScreenBlue(
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(text = title)
    }
}
```
which you can navigate to like so...
```kotlin
navController.navigateToScreenBlue("Title")
```

We will be creating a Maven artefact in due course.
