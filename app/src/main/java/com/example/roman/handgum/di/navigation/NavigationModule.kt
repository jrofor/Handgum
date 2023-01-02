package com.example.roman.handgum.di.navigation

import com.example.roman.handgum.di.AppScope
import com.example.roman.handgum.navigation.NavProviderKeeper
import com.example.roman.handgum.navigation.NavigationActionProviderImpl
import com.example.roman.handgum.navigation.NavigationDispatcher
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {

    @AppScope
    @Binds
    abstract fun navDispatcher(impl: NavigationActionProviderImpl): NavigationDispatcher

    @AppScope
    @Binds
    abstract fun navProviderKeeper(impl: NavigationActionProviderImpl): NavProviderKeeper

}