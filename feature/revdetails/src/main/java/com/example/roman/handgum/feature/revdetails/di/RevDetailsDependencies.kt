package com.example.roman.handgum.feature.revdetails.di

import com.example.roman.handgum.core.di.Dependencies
import com.example.roman.handgum.navigationapi.revdetails.LocalRevDetailsNavigator

interface RevDetailsDependencies : Dependencies {
    val localNavigator: LocalRevDetailsNavigator
}