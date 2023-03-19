plugins {
    id("handgum.android.library")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.example.roman.handgum.feature.revlist"

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