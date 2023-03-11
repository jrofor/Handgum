// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    //Specify from which repositories we want to get gradle plugins
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
// to use, enter in the terminal:
// ./gradlew dependencyUpdates
// or init in AS Tool appName->Tasks->help->dependencyUpdates
// Report: build/dependencyUpdates/report.txt
    apply(plugin = "com.github.ben-manes.versions")
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
// reject all non stable versions and disallow release candidates as upgradable versions from stable versions
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

// we register a task named type and when it is called,
// we delete the generated build folder from the root of the project
// bad for multi platforms
task("clean") {
    delete(rootProject.buildDir)
}