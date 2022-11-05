package com.example.roman.handgum.database.di.repository

import com.example.roman.handgum.database.repositoryImpl.RevRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * @author rofor
 */
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun revRepository(revRepImpl: RevRepositoryImpl): com.example.roman.handgum.commonentity.db.repository.RevRepository

}