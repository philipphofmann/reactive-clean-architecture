# Multi Module Reactive Clean Architecture

The app displays a screen that the user can tap on. Each tap of the user increases the tap count as
 well the tap level of the user. The purpose of this app is to demonstrate how a reactive
 [Clean Architecture][1] with Room, Coroutines, multiple modules and the [Android Jetpack ViewModels][2]
 can be achieved on Android.


The app is structured into four different main parts:
* [Features](./features) - contains the user facing features of the app.
* [Common](./common) - contains reusable modules that are used in multiple parts of the app.
* [Libs](./libs) - contains modules with utility classes for libraries like RxJava or Room.
* [App](./app) - is the main component. It knows all components and sets up the app. It should be 
kept as small as possible.

[1]: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
[2]: https://developer.android.com/topic/libraries/architecture/viewmodel