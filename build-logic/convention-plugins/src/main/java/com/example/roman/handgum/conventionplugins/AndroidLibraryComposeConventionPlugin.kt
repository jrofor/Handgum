package com.example.roman.handgum.conventionplugins

import com.android.build.gradle.LibraryExtension
import com.example.roman.handgum.conventionplugins.utils.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}