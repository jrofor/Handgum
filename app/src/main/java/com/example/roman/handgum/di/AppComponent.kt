package com.example.roman.handgum.di

import android.content.Context
import com.example.roman.handgum.App
import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.database.di.DatabaseDependencies
import com.example.roman.handgum.di.database.DatabaseDependenciesInterfaceModule
import com.example.roman.handgum.di.database.DatabaseDependenciesModule
import com.example.roman.handgum.di.depsinterface.RevDetailsDependenciesInterfaceModule
import com.example.roman.handgum.di.depsinterface.RevListDependenciesInterfaceModule
import com.example.roman.handgum.di.navigation.FeaturesNavigationModule
import com.example.roman.handgum.di.navigation.NavigationModule
import com.example.roman.handgum.di.network.ApiNetworkDependenciesModule
import com.example.roman.handgum.feature.revdetails.di.RevDetailsDependencies
import com.example.roman.handgum.feature.revdlist.di.RevListDependencies
import com.example.roman.handgum.ui.activity.di.ActivityBuilderModule
import com.example.roman.handgum.ui.fragment.feature.di.FeatureComponent
import com.example.roman.handgum.ui.fragment.feature.di.FeatureModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Scope

/**
 * @author rofor
 */
@Scope
annotation class AppScope

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelBuilderModule::class,
        //provides dependencies from itself module for classes in other modules
        DatabaseDependenciesInterfaceModule::class,
        DatabaseDependenciesModule::class,
        ApiNetworkDependenciesModule::class,
        NavigationModule::class,
        FeaturesNavigationModule::class,
        RevListDependenciesInterfaceModule::class,
        RevDetailsDependenciesInterfaceModule::class,
        FeatureModule::class,
    ]
)
@AppScope
interface AppComponent :
    DatabaseDependencies,
    RevListDependencies,
    RevDetailsDependencies {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(App: App)
    fun featureComponent(): FeatureComponent.Factory
}