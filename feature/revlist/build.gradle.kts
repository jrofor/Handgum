plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {

    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

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

    implementation(project(":core"))
    implementation(project(":navigation-api"))
    implementation(project(":common-entity"))

    //Views
    implementation(libs.bundles.ui.base.libs)
    implementation(libs.ui.swiperefresh)
    implementation(libs.ui.recyclerview)
    //ImageLoader
    implementation(libs.glide)
    // Dagger
    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)
    // Navigation
    implementation(libs.bundles.navigation)
    //lifecycle
    implementation(libs.lifecycle)
    implementation(libs.livedata)
    //rxjava2
    implementation(libs.bundles.rxjava)
    //Log
    implementation(libs.timber)

    implementation(libs.bundles.ui.base.libs)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidtest)
}