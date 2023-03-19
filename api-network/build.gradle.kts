plugins {
    id("handgum.android.library")
}

android {

    defaultConfig {
        //network constants
        buildConfigField("long", "TIMEOUT", "180")
        buildConfigField("String", "BASE_URL", "\"https://api.nytimes.com/\"")
        buildConfigField("String", "API_KEY", "\"xTvrIaqsho3wZBAPUcJP7H8KqBl9cSaG\"")
        signingConfig = signingConfigs.getByName("debug")
    }
    namespace = "com.example.roman.handgum.apinetwork"
}

dependencies {

    implementation(project(":common-entity"))

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)

}