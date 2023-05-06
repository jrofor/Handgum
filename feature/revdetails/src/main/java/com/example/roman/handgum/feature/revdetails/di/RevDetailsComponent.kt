package com.example.roman.handgum.feature.revdetails.di

import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.feature.revdetails.view.RevDetailsFragment
import dagger.Component

/**
 * @author rofor
 */
@Component(
    dependencies = [RevDetailsDependencies::class],
    modules = [
        RevDetailsModule::class,
        ViewModelBuilderModule::class
    ]
)
internal interface RevDetailsComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: RevDetailsDependencies): RevDetailsComponent
    }

    fun inject(fragment: RevDetailsFragment)
}