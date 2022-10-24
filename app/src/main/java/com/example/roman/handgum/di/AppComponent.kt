package com.example.roman.handgum.di

import android.content.Context
import com.example.roman.handgum.apinetwork.di.ApiNetworkDependenciesModule
import com.example.roman.handgum.core.di.ViewModelBuilderModule
import com.example.roman.handgum.database.di.DatabaseDependencies
import com.example.roman.handgum.ui.fragment.feature.di.FeatureComponent
import com.example.roman.handgum.ui.fragment.revlist.di.RevListComponent
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
    ]
)
interface AppComponent : DatabaseDependencies {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun context(): Context

    fun revListComponent(): RevListComponent.Factory
    fun featureComponent(): FeatureComponent.Factory
}