package com.example.roman.handgum.di

import android.content.Context
import com.example.roman.handgum.App
import com.example.roman.handgum.apinetwork.di.ApiNetworkDependenciesModule
import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.database.di.DatabaseDependencies
import com.example.roman.handgum.feature.revdlist.di.RevListDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author rofor
 */

@Singleton
@Component(
    modules = [
        //provides dependencies from itself module for classes in other modules
        DatabaseDependenciesModule::class,
        ApiNetworkDependenciesModule::class,
        ViewModelBuilderModule::class,
        RevListDependenciesModule::class,
    ]
)
interface AppComponent :
    DatabaseDependencies, RevListDependencies {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(App: App)
    fun context(): Context

    //fun featureComponent(): FeatureComponent.Factory
}