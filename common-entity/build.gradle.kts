plugins {
    id("handgum.android.library")
}

android {
    namespace = "com.example.roman.handgum.commonentity"
}

dependencies {

    implementation(libs.gson)
    implementation(libs.bundles.rxjava)
}