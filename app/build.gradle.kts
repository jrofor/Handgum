plugins {
    id("handgum.android.application")
}

android {

    defaultConfig {
        applicationId = "com.example.roman.handgum"
        versionCode = 1
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        namespace = "com.example.roman.handgum"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
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