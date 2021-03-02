[![Build Status](https://travis-ci.com/adsf117/MyKitsu_AAC.svg?branch=main)](https://travis-ci.com/adsf117/MyKitsu_AAC)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io)
[![codecov](https://codecov.io/gh/adsf117/MyKitsu_AAC/branch/main/graph/badge.svg?token=41WRNB3LQT)](https://codecov.io/gh/adsf117/MyKitsu_AAC)
[![CodeFactor](https://www.codefactor.io/repository/github/adsf117/mykitsu_aac/badge)](https://www.codefactor.io/repository/github/adsf117/mykitsu_aac)

# Kitsu Android Architecture Components

[Dependency Inversion Principle (DIP)](https://martinfowler.com/articles/dipInTheWild.html)(without frameworks)

[Guide to app architecture MVVM](https://developer.android.com/jetpack/docs/guide) 

[Project Backlog](https://github.com/adsf117/MyKitsu_AAC/projects/1) 

[Continuous Integration](https://www.martinfowler.com/articles/continuousIntegration.html) [Using Tavis](https://travis-ci.com/)
(WIP I've facing issues publish the Coverage and running the Android Test)



## How it looks

![alt text](https://github.com/adsf117/MyKitsu_AAC/blob/documentation/readmefile/demo.gif)

## Architecture

There are three main layers: 

data: in this module has all details Service and Database 
repository : his responsibility is to sync server data with the local,  taking as one source of true the database (Room)  
presentation: This module has all android framework using MVVM 

![alt text](https://github.com/adsf117/MyKitsu_AAC/blob/documentation/readmefile/Arquitecture.png)

## Troubleshooting

if you get an error running Android test remenber disabled the animation you can do it executing the next shell commands:
adb shell settings put global window_animation_scale 0.0
adb shell settings put global transition_animation_scale 0.0
adb shell settings put global animator_duration_scale 0.0
[More info](https://testyour.app/blog/emulator)


## Libraries Used :
* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [Navigation Component](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#0)
* [Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQiA4feBBhC9ARIsABp_nbVSzmSdBQuAKP2WhE9fTRDmz2u67AtgL7wFOrs5kgcNKuqHWPbA3mEaAsSJEALw_wcB&gclsrc=aw.ds) 
* [Flow](https://developer.android.com/kotlin/coroutines/additional-resources) 
* [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) 
* [Data Binding](https://codelabs.developers.google.com/codelabs/android-databinding/index.html?index=..%2F..index#5) 
* [View Binding](https://developer.android.com/topic/libraries/view-binding) 
* [Retrofit2](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide) 
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin#mockito-kotlin)


## References:

Udacity:

* [Build your First app Lesson 7 (RecyclerView 11.Improving Data Refresh)](https://classroom.udacity.com/courses/ud9012/lessons/ee5a525f-0ba3-4d25-ba29-1fa1d6c567b8/concepts/7047e569-b5a2-4767-a589-6fb4e8e367d4)
* [Advanced Android with Kotlin Lesson 7 Using Espresso to Test a Fragment ](https://classroom.udacity.com/courses/ud940/lessons/9434e029-dce7-4550-93f2-18a224433e72/concepts/9110996b-f881-4b5d-9c5b-a90094eac3c7)
* [Advanced Android with Kotlin](https://classroom.udacity.com/courses/ud940)
* [Lesson 7 - 3.2 Using MotionLayout to Animate Android Apps](https://classroom.udacity.com/courses/ud940/lessons/cd57b5a4-076d-42db-b067-e30ca84b0502/concepts/dc303b40-1499-42ac-98bd-212085d8cbac)

Google Codelabs:
* [Kotlin Bootcamp Course](https://codelabs.developers.google.com/kotlin-bootcamp/)
* [Navigation Codelab](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#6)
* [Android Room with a View - Kotlin](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0)
* [Using Kotlin Coroutines in your Android App](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/index.html?index=..%2F..index#6)
* [MotionLayout](https://codelabs.developers.google.com/codelabs/motion-layout/index.html?index=..%2F..index#8)
* [Espresso for UI testing](https://codelabs.developers.google.com/codelabs/android-training-espresso-for-ui-testing/index.html#3)
* [Android Paging](https://developer.android.com/codelabs/android-paging#0)


Raywenderlich:
* [Jetpack navigation controller](https://www.raywenderlich.com/5365-jetpack-navigation-controller)
* [Android Architecture Components: Getting Started](https://www.raywenderlich.com/164-android-architecture-components-getting-started)
* [android architecture components livedata](https://www.raywenderlich.com/4980-android-architecture-components-livedata)
* [android architecture components viewmodel](https://www.raywenderlich.com/5046-android-architecture-components-viewmodel)


Others:
* [Mocking Coroutines](https://proandroiddev.com/mocking-coroutines-7024073a8c09)
* [Dealing with actions and checks on children of a RecyclerView’s item in Espresso tests](https://medium.com/@xabaras/dealing-with-actions-and-checks-on-children-of-a-recyclerviews-item-in-espresso-tests-dabd93361810)
* [Testing Navigation](https://developer.android.com/guide/navigation/navigation-testing)


