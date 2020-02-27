# Android app for personal notes management

## Prerequisites
* Android SDK v21 and above
* Latest Android Build Tools
* Gradle 6.2.1

## Installation

1. Download or clone this repository
2. Open project in Android Studio
3. Sync project with Gradle files
4. Run 'app' solution on your mobile phone or emulator

## Technlogies

* Android
* Jetpack
* MVVM
* Kotlin
* Live Data
* Kotlin coroutines
* Retrofit
* Mockito

## Architecture
App is developed as a single activity with two fragments and is written in Kotlin language.

It uses Android [Navigation Component](https://developer.android.com/guide/navigation) for navigating between screens.

Async work - such as API calls handled by Retrofit - are done via Kotlin coroutines.

UI states are held in View Models and propagated via Live data values.