package com.example.roman.handgum.di.depsinterface

import com.example.roman.handgum.core.di.Dependencies
import com.example.roman.handgum.core.di.DependenciesKey
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.di.AppScope
import com.example.roman.handgum.feature.revdlist.di.RevListDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RevListDependenciesInterfaceModule {

    @Binds
    @IntoMap
    @AppScope
    @DependenciesKey(RevListDependencies::class)
    fun bindRevListDeps(impl: AppComponent): Dependencies
}