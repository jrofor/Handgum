plugins {
    id("handgum.android.library")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.example.roman.handgum.feature.revdetails"

}

dependencies {

    implementation(project(":core"))
    implementation(project(":navigation-api"))

    implementation(libs.bundles.ui.base.libs)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidtest)

    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)

    implementation(libs.bundles.navigation)

    implementation(libs.lifecycle)
    implementation(libs.livedata)
}