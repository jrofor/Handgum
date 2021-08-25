package com.example.roman.handgum.di

import android.content.Context
import com.example.roman.handgum.ui.fragment.revdetails.di.RevDetailsComponent
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
        AppModule::class,
        ViewModelBuilderModule::class,
        MapperModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun context(): Context

    fun revListComponent(): RevListComponent.Factory

    fun revDetailsComponent(): RevDetailsComponent.Factory
}