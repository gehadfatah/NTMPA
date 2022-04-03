# NewTimesMostPopularArticles

##Introduction

NYTimes Api Integration

Demo NYTimes application using Coroutines, ViewModel, clean architecture ,Navigation Component, MVVM ,Repository pattern ,Android Architecture components to display most popular articles in New York times news
read  most viewed news in 7  days

##UI approach ,architecture pattern

Using clean architecture with MVVM architecture pattern


##Libraries used
This module requires the following modules/libraries:

.Kotlin

.Androidx

.navigation component

.Room (favourite items)

.Android ViewModel

.Android LiveData

.MVVM and clean architecture

.Coroutines

.Retrofit2

.Mockito

.simple jenkins

.fastlane to distribute apk

## Unit Testing Frameworks

Unit Tests verify the interactions of viewmodels between repositories and dao & REST api requests.
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) - a small library that provides helper functions to work with Mockito in Kotlin.


##To run app:

-clone repository

-run build

-set account in new times api

-add your api key to build.gradle file

* **Add your [News API][13] Key in `build.gradle` file to make news fetch work.**


[13]: https://developer.nytimes.com/get-started



##To build app from android studio terminal:
```
gradle build
```

##To run tests:

```
gradle test
```

You may view test results at ./app/build/reports/tests/testDevDebugUnitTest/index.html.

## Future enhancement app:


-add kotlin-dsl with center build src to control dependencies

-enhancement in ui

-add more unit testing , ui test

-use Mockk library for testing

-use viewBinding

-use koin for di

##to show lint

It use to be that you could add gradle tasks after certain actions in android studio.

Open up the Gradle tab on the right side of the screen

Select your task


lintDebug 
or
lintRelease

```

OR choose choose Analyze > Inspect Code
```






