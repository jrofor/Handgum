package com.example.roman.handgum.feature.revdlist.di

import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.feature.revdlist.RevListFragment
import dagger.Component

/**
 * @author rofor
 */
@Component(
    dependencies = [RevListDependencies::class],
    modules = [
        RevListModule::class,
        ViewModelBuilderModule::class
    ]
)
internal interface RevListComponent {
    @Component.Factory
    interface Factory {
        fun create(daps: RevListDependencies): RevListComponent
    }

    fun inject(fragment: RevListFragment)
}