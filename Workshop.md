---
title: "Example Android: Unit Testing"
subject: "An example to learn how to unit test Android applications with Android Annotations, Robolectric, Mocktito and Jacoco"
titlepage: true
titlepage-color: AF9162
titlepage-text-color: ffffff
titlepage-rule-color: ffffff
titlepage-rule-height: 1
toc-title: "Table of contents"
...

Prerequisites
=============
To use this example you need to install the following tools:

* [Android Studio 2.3](http://developer.android.com/sdk/index.html)
* The source and build files of this project :)

-------

Unit Testing
============

-------

Testing
-------
For testing Android apps, you typically create [these types of automated unit tests](https://developer.android.com/training/testing/unit-testing/index.html):

* Local tests: Unit tests that run on your local machine only. These tests are compiled to run locally on the Java Virtual Machine (JVM) to minimize execution time. Use this approach to run unit tests that have no dependencies on the Android framework or have dependencies that can be filled by using mock objects.

-------

* Instrumented tests: Unit tests that run on an Android device or emulator. These tests have access to instrumentation information, such as the Context for the app under test. Use this approach to run unit tests that have Android dependencies which cannot be easily filled by using mock objects.

-------

Preface
-------
Suppose you want to build an Android application in a TDD-style. There are a few nasty things about Android that makes unit testing more difficult than for normal Java applications.

-------

* Android source code is Java but it is compiled by the Dalvik compiler to dex. To run it you need an Android device or emulator.
* In a standard Android Application the Android API is everywhere in your code. The Android code is already tested so to only test _your_ application we need to leave Android code outside scope as much as possible. For me _unit testing_ means testing _my_ code apart from the framework (I consider Android as a library and a [framework](https://martinfowler.com/bliki/InversionOfControl.html)). 

-------

This codebase uses a few libraries to simplify unit testing for Android applications:

* Android Annotations (if you need more power and be able to handle more complex situations consider ButterKnife for UI binding and Dagger 2 for dependency injections, AA does both)
* Robolectric

-------

There are also good design and architecture choices to make that simplify unit testing but this example focuses on a simple application and some libraries. Some resources that might help to improve the design of your Android application are:

* [Android Clean Architecture](https://github.com/android10/Android-CleanArchitecture)
* [Effective Android Architecture](https://www.youtube.com/watch?v=bXRI7YAha1M)

-------


Android Annotations
===================

--------

Android Annotations
-------------------

* AndroidAnnotations is an Open Source annotation processing library that speeds up Android development
* It takes care of the plumbing, and lets you concentrate on what's really important.
* By simplifying your code, it facilitates its maintenance.

-------

Annotation Processing
---------------------

* Annotation Processing is a feature of the Java compiler that lets you write a library to process source code annotations at compile time
* Runs your annotation processing code in the compiler and can generate new source code files that will also be compiled
* There is no way with public API to modify existing classes

-------

How does it work
----------------

* AndroidAnnotations is a Java Annotation Processing Tool that generates Android specific source code based on annotations
* It enhances a class by subclassing it with a generated class that adds and overrides methods in the annotated class
* The generated class is named by appending an underscore to the name of the annotated class

-------

How do you use it
-----------------
Everywhere in the project that you refer to an enhanced class you append an underscore to the class name:

* In the Android Manifest
* In layout resources
* In the code, even in your test code

-------

Example: Activity and Views
---------------------------
```{.java include="src/main/java/com/example/activity/DeckardActivity.java" start="15" stop="22"}
```
```{.java include="src/main/java/com/example/activity/DeckardActivity.java" start="37" stop="37"}
```

-------

Example: Bean and EBean
-----------------------
```{.java include="src/main/java/com/example/activity/DeckardActivity.java" start="15" stop="16"}
```
```{.java include="src/main/java/com/example/activity/DeckardActivity.java" start="22" stop="37"}
```

-------

Example: Background and UIThread
--------------------------------
```{.java include="src/main/java/com/example/activity/ButtonClickListener.java" start="13" stop="42"}
```

-------

Android Annotations in Gradle
-----------------------------
```{.yaml include="build.gradle" start="65" stop="67"}
```
```{.yaml include="build.gradle" start="76" stop="76"}
```

-------

Mocking
=======

--------

JUnit and Mockito in Gradle
---------------------------
Just like in Maven:

```{.yaml include="build.gradle" start="65" stop="65"}
```
```{.yaml include="build.gradle" start="73" stop="74"}
```
```{.yaml include="build.gradle" start="76" stop="76"}
```

-------

Robolectric
-----------
Running tests on an Android emulator or device is slow! Building, deploying, and launching the app often takes a minute or more. That’s no way to do TDD. There must be a better way.

-------

Unit testing an Activity
------------------------
Suppose you want to unit test and activity. You try this:

```java
public class DeckardActivityTest {

    @Test
    public void whenActivityCreatedAppDependenciesAreSetup() throws Exception {
        DeckardActivity deckardActivity = new DeckardActivity();
        assertNotNull(deckardActivity.buttonClickHandler);
    }
}
```

When you run this test, the console says: ["Error: 'Method ... not mocked'"](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html)

-------

Error: "Method ... not mocked"
------------------------------
If you run a test that calls an API from the Android SDK that you do not mock, you'll receive an error that says this method is not mocked. That's because the android.jar file used to run unit tests does not contain any actual code (those APIs are provided only by the Android system image on a device).

Instead, all methods throw exceptions by default. 

-------

Running unit tests without Android device
-----------------------------------------
[Robolectric](http://robolectric.org/) is a unit test framework that de-fangs the Android SDK jar so you can test-drive the development of your Android app. Tests run inside the JVM on your workstation in seconds. With Robolectric and Android Annotations you can write tests like this:

-------


```{.java include="src/test/java/com/example/activity/DeckardActivityTest.java" start="10" stop="18"}
```

-------

Robolectric in Gradle
---------------------
```{.yaml include="build.gradle" start="65" stop="65"}
```
```{.yaml include="build.gradle" start="75" stop="76"}
```

-------

Noticed the _ after DeckardActivity? This is the version that comes from Android Annotations (AA) annotation processor. When you use this version all annotations like @Activity and @Bean are resolved. Decide per test cases if you need the real dependencies for @Bean or override the properties with test doubles like stubs or mocks.

-------

Test Doubles
------------

![Figure 1: Different Test Doubles](http://xunitpatterns.com/Types%20Of%20Test%20Doubles-implementation.gif)

-------

Unit vs Integration Tests
=========================

--------

Unit vs Integration Tests
-------------------------

The DeckardActivityTest used the real dependency on RestDataController so it is in fact an integration test. Let's look at a class that we can unit test:

```{.java include="src/main/java/com/example/activity/ButtonClickListener.java" start="13" stop="42"}
```

-------

The ButtonClickListener had two dependencies:

* The DeckardActivity
* The RestDataController

Both dependencies can be mocked:

-------

Drinking Mockito when you're in AA :)
-------------------------------------

```{.java include="src/test/java/com/example/activity/ButtonClickListenerTest.java" start="16" stop="43"}
```

-------

REST
====

--------

Doing REST calls in Android
----------------------------
We saw the RestDataController as a dependency for the ButtonClickListener, but how to do REST calls from an Android device?

There are two things things to consider:

* Android does not allow you to access services on the main thread, luckily AA has @Background and @UIThread (but you can do this with standard Android [too](https://developer.android.com/guide/components/processes-and-threads.html))
* You need to alter the AndroidManifest.xml:

```{.xml include="src/main/AndroidManifest.xml" start="15" stop="15"}
```

-------

REST in Android: Retrofit2
--------------------------
There are different options/APIs to do REST calls:
 
* JAX-RS Client, like Jersey. Heavyweight, has an own DI container (HK2) built on Guice. 
* Spring RestTemplate (or use AA's annotation to access this template). Can cause dependency problems with HTTP Commons.
* Use Retrofit2, this is my favourite.

-------

Retrofit in Gradle
------------------

```{.yaml include="build.gradle" start="65" stop="65"}
```
```{.yaml include="build.gradle" start="69" stop="71"}
```
```{.yaml include="build.gradle" start="76" stop="76"}
```

-------

Retrofit in your code
---------------------

With Retrofit the REST interface is mapped to a Java interface like this:

```{.java include="src/main/java/com/example/activity/JsonPlaceHolderAPI.java" start="8" stop="13"}
```

-------

Retrofit in your code
---------------------
Using the Retrofit Builder and a JSON Mapper we can offer a simple Java interface to a client:

```{.java include="src/main/java/com/example/activity/RetrofitBuilder.java" start="11" stop="19"}
```

-------

Retrofit in your code
---------------------
And the client can use the interface to call methods just like it's a local method invocation:

```{.java include="src/main/java/com/example/activity/RestDataController.java" start="11" stop="29"}
```

-------

Testing the RestDataController
------------------------------
To see if the RestDataController really gets the data from our API we can create a simple integration test. We don't need Robolectric here because there are no Android API dependencies to shadow:

```{.java include="src/test/java/com/example/activity/RestDataControllerIntegrationTest.java" start="7" stop="14"}
```

-------

Running tests and getting test coverage
=======================================

--------

Running tests in Gradle
-----------------------

```yaml
./gradlew test
```

-------

Getting test coverage for Android projects
-------------------------------------------

Jacoco is a tool for Java application that is able to generate a test coverage file that can be read by SonarQube. We need the following adjustments to build.gradle:

```{.yaml include="build.gradle" start="21" stop="21"}
```

```{.yaml include="build.gradle" start="36" stop="40"}
```

-------


```{.yaml include="build.gradle" start="78" stop="102"}
```

Resources
=========

* [Building Local Tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html)
* [Building Effective Unit Tests](https://developer.android.com/training/testing/unit-testing/index.html)
* [Unit Test your Application](https://github.com/androidannotations/androidannotations/wiki/Unit-test-your-application)
* [Android Annotations Cookbook](https://github.com/androidannotations/androidannotations/wiki/Cookbook)
