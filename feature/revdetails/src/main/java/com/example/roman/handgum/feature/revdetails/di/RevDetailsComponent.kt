package com.example.roman.handgum.feature.revdetails.di

import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.feature.revdetails.RevDetailsFragment
import dagger.Component

/**
 * @author rofor
 */
@Component(
    modules = [
        RevDetailsModule::class,
        ViewModelBuilderModule::class
    ]
)
interface RevDetailsComponent {
    @Component.Factory
    interface Factory {
        fun create(): RevDetailsComponent
    }

    fun inject(fragment: RevDetailsFragment)
}