plugins {
    id("handgum.android.library")
}

android {
    namespace = "com.example.roman.handgum.core"
}

dependencies {

    kapt(libs.bundles.di.dagger.kapt)
    implementation(libs.bundles.di.dagger)
    implementation(libs.bundles.rxjava)
    implementation(libs.bundles.navigation)
    implementation(libs.timber)
}