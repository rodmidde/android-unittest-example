# Android Unit test example

## Setup

*Note: These instructions assume you have a Java 1.8 [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.*

1. Install [Android Studio 2.3](http://developer.android.com/sdk/index.html).
2. Open the project.

### Running on the command line

1. In the project directory you should be able to run the unit tests:
    ```bash
    ./gradlew test
    ```
2. Finally you can build a debug `.apk` of the project for installation on phones:
    ```bash
    ./gradlew assemble
    ```
    This will output the file to `build/outputs/apk/*-debug.apk`
