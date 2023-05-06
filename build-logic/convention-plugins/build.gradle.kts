plugins {
    `kotlin-dsl`
}

group = "com.example.roman.handgum.buildlogic"

dependencies {
    compileOnly(libs.plugin.android)
    compileOnly(libs.plugin.kotlin)
    compileOnly(libs.plugin.navigation.args)
    compileOnly(libs.plugin.gver)
}

gradlePlugin {
    plugins {
        register("gradleAndroidApplication") {
            id = "handgum.android.application"
            implementationClass =
                "com.example.roman.handgum.conventionplugins.AndroidApplicationConventionPlugin"
        }
    }
    plugins {
        register("gradleAndroidLibrary") {
            id = "handgum.android.library"
            implementationClass =
                "com.example.roman.handgum.conventionplugins.AndroidLibraryConventionPlugin"
        }
    }
    plugins {
        register("androidLibraryCompose") {
            id = "handgum.android.library.compose"
            implementationClass =
                "com.example.roman.handgum.conventionplugins.AndroidLibraryComposeConventionPlugin"
        }
    }
}