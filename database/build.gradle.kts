plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.example.roman.handgum.database"

    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        buildConfigField("int", "DB_VERSION", "1")
        buildConfigField("String", "DB_FILE_NAME", "\"handgum.db\"")
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
}

dependencies {

    implementation(project(":core"))
    implementation(project(":common-entity"))

    implementation(libs.bundles.di.dagger)
    kapt(libs.bundles.di.dagger.kapt)

    kapt(libs.room.kapt)
    implementation(libs.room)

    kapt(libs.mapstruct.kapt)
    implementation(libs.mapstruct)

}