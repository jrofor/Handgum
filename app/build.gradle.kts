plugins {
    id("com.android.application")
    id("kotlin-kapt")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roman.handgum"
        minSdk = 21
        targetSdk = 33
        versionCode = 1

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":api-network"))
    implementation(project(":database"))
    implementation(project(":core"))
    implementation(project(":common-entity"))
    implementation(project(":feature:revdetails"))
    implementation(project(":feature:revlist"))
    implementation(project(":navigation"))
    implementation(project(":navigation-api"))

    /*
    * Tests
    */
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidtest)

    /*
    * Views
    */
    implementation(libs.bundles.ui.base.libs)

    /*
    * Lifecycle
    */
    implementation(libs.lifecycle)
    implementation(libs.livedata)

    /*
    * Log
    */
    implementation(libs.timber)

    /*
    * Dagger
    */
    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)

    /*
    * Navigation
    */
    implementation(libs.bundles.navigation)

    /*
    * rxjava2
    */
    implementation(libs.bundles.rxjava)
}