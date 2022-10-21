package com.example.roman.handgum.database.di

import com.example.roman.handgum.database.di.mappers.MappersModule
import com.example.roman.handgum.database.di.repository.RepositoriesModule
import com.example.roman.handgum.database.domain.mappers.ReviewMapper
import com.example.roman.handgum.database.repositoryImpl.RevRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [DatabaseDependencies::class],
    modules = [
        DatabaseModule::class,
        RepositoriesModule::class,
        MappersModule::class,
    ]
)
interface DatabaseComponent {
    @Component.Factory
    interface Factory {
        //DatabaseDependencies provides context from AppComponent
        //Before that, the context was directly specified when
        // the DatabaseComponent was located in the app module
        //fun create(@BindsInstance applicationContext: Context): DatabaseComponent
        fun create(deps: DatabaseDependencies): DatabaseComponent
    }

    //the variable is specified to pass as a dependency to other modules
    // by creating a Factory for it
    val revRepository: RevRepositoryImpl
    val revMapper: ReviewMapper
}
