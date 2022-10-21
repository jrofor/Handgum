package com.example.roman.handgum.di

import com.example.roman.handgum.database.di.mappers.RevMapperFactory
import com.example.roman.handgum.database.di.repository.RevRepositoryFactory
import com.example.roman.handgum.database.domain.mappers.ReviewMapper
import com.example.roman.handgum.database.repository.RevRepository
import dagger.Module
import dagger.Provides

@Module
class DatabaseDependenciesModule {

    @Provides
    fun revRepository(depsImpl: AppComponent): RevRepository = RevRepositoryFactory.create(depsImpl)

    @Provides
    fun revMapper(depsImpl: AppComponent): ReviewMapper = RevMapperFactory.create(depsImpl)

}