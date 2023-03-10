plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        //network constants
        buildConfigField("long", "TIMEOUT", "180")
        buildConfigField("String", "BASE_URL", "\"https://api.nytimes.com/\"")
        buildConfigField("String", "API_KEY", "\"xTvrIaqsho3wZBAPUcJP7H8KqBl9cSaG\"")
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = false;
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
}

dependencies {

    implementation(project(":common-entity"))

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)

}