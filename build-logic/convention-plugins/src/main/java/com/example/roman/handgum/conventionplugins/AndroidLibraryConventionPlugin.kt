package com.example.roman.handgum.conventionplugins

import com.android.build.gradle.LibraryExtension
import com.example.roman.handgum.conventionplugins.utils.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("androidx.navigation.safeargs.kotlin")
                apply("kotlin-kapt")
            }

            extensions.configure<LibraryExtension> {
                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
                configureKotlinAndroid(this, libs)
                defaultConfig.targetSdk =
                    libs.findVersion("targetSdk").get().requiredVersion.toInt()

                buildFeatures {
                    viewBinding = true
                }
            }/*
            extensions.configure<JavaPluginExtension> {
                toolchain {
                    languageVersion.set(JavaLanguageVersion.of(11))
                }
            }*/
        }
    }

}