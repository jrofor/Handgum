package com.example.roman.handgum.di

import com.example.roman.handgum.data.db.repository.RevRepository
import com.example.roman.handgum.data.db.repositoryImpl.RevRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * @author rofor
 */
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun revRepository(RevRepImpl: RevRepositoryImpl): RevRepository

}