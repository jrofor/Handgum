// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories { //Specify from which repositories we want to get gradle plugins
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
    dependencies {
        classpath(libs.bundles.plugins)
    }
}

allprojects {
//used in all connected build.gradle in the project () (as another option to specify subprojects{})
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}

// we register a task named type and when it is called,
// we delete the generated build folder from the root of the project
// bad for multi platforms
task("clean") {
    delete(rootProject.buildDir)
}