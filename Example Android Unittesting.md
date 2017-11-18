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

Preface
=======
Suppose you want to build an Android application in a TDD-style. There are a few nasty things about Android that makes unit testing more difficult than for normal Java applications:

* Android source code is Java but it is compiled by the Dalvik compiler to dex. To run it you need an Android device or emulator.
* In a standard Android Application the Android API is everywhere in your code. The Android code is already tested so to only test _your_ application we need to leave Android code outside scope as much as possible. For me _unit testing_ means testing _my_ code apart from the framework (I consider Android as a library and a [framework](https://martinfowler.com/bliki/InversionOfControl.html)). 

This codebase uses a few libraries to simplify unit testing for Android applications:

*

There are also good design and architecture choices to make that simplify unit testing but this example focuses on a simple application and some libraries. Some resources that might help to improve the design of your Android application are:

* [Android Clean Architecture](https://github.com/android10/Android-CleanArchitecture)
* [Effective Android Architecture](https://www.youtube.com/watch?v=bXRI7YAha1M)


Resources
=========
[Building Local Tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html)
[Building Effective Unit Tests](https://developer.android.com/training/testing/unit-testing/index.html)
[Unit Test your Application](https://github.com/androidannotations/androidannotations/wiki/Unit-test-your-application)
[Android Annotations Cookbook](https://github.com/androidannotations/androidannotations/wiki/Cookbook)
