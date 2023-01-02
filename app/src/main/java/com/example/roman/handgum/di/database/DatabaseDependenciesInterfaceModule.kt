package com.example.roman.handgum.di.database

import com.example.roman.handgum.core.di.Dependencies
import com.example.roman.handgum.core.di.DependenciesKey
import com.example.roman.handgum.database.di.DatabaseDependencies
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.di.AppScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DatabaseDependenciesInterfaceModule {

    @Binds
    @IntoMap
    @AppScope
    @DependenciesKey(DatabaseDependencies::class)
    fun bindDatabaseDeps(impl: AppComponent): Dependencies
}