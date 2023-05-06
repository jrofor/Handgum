plugins {
    id("handgum.android.library")
}

android {

    defaultConfig {
        buildConfigField("int", "DB_VERSION", "2")
        buildConfigField("String", "DB_FILE_NAME", "\"handgum.db\"")

        javaCompileOptions.annotationProcessorOptions {
            argument("room.schemaLocation", "$projectDir/schemas")
        }
    }

    namespace = "com.example.roman.handgum.database"
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