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
fun ScreenRed() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}
```
Cartographer will generate an extension on `NavGraphBuilder` that it will add to an auto generated
`NavHost` currently called `AppNavHost`.

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
To use the AppNavHost in a project just add it to your `@Composable` graph where you want it such as
in your `Activity`s `onCreate()` method.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) { 
    super.onCreate(savedInstanceState)
    setContent {
        CartographerTheme {
            AppNavHost(
                modifier = Modifier.padding(it),
                startDestination = "ScreenRed",
                navController = navController
            )
        }
    }
}
```
### Things coming to Cartographer
- We will be adding a `startDestination` field to the `@Destination` so that you can specify it
should start there.
- We will be creating a Maven artefact in due course.
- We will be adding the ability to create multiple `NavHost`s in the near future via a field in the
`@Destination`.
- Currently only Int Bool String and Long ar supported. Support for the remaining argument types 
will follow shortly.
