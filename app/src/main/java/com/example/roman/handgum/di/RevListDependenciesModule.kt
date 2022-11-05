package com.example.roman.handgum.di

import com.example.roman.handgum.core.di.Dependencies
import com.example.roman.handgum.core.di.DependenciesKey
import com.example.roman.handgum.feature.revdlist.di.RevListDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RevListDependenciesModule {

    @Binds
    @IntoMap
    @DependenciesKey(RevListDependencies::class)
    fun bindRevListDeps(impl: AppComponent): Dependencies
}