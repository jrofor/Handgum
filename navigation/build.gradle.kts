plugins {
    id("handgum.android.library")
}

android {
    namespace = "com.example.roman.handgum.navigation"
}

dependencies {

    implementation(project(":navigation-api"))
    implementation(project(":feature:revdetails"))

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)
}