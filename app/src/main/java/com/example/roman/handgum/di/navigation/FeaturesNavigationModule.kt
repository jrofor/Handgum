package com.example.roman.handgum.di.navigation

import com.example.roman.handgum.di.AppScope
import com.example.roman.handgum.navigationapi.revdetails.LocalRevDetailsNavigator
import com.example.roman.handgum.navigation.NavigationActionProviderImpl
import com.example.roman.handgum.navigation.revdetails.LocalRevDetailsNavigatorImpl
import com.example.roman.handgum.navigation.revdetails.RevDetailsNavigatorImpl
import com.example.roman.handgum.navigationapi.revdetails.RevDetailsNavigator
import dagger.Module
import dagger.Provides

@Module
class FeaturesNavigationModule {

    @AppScope
    @Provides
    fun navActionProvider(): NavigationActionProviderImpl = NavigationActionProviderImpl()

    @Provides
    fun revDetailsNavigator(impl: RevDetailsNavigatorImpl): RevDetailsNavigator = impl

    @Provides
    fun localRevDetailNav(localNavImpl: LocalRevDetailsNavigatorImpl): LocalRevDetailsNavigator = localNavImpl
}
