package com.example.roman.handgum.apinetwork.di

import com.example.roman.handgum.apinetwork.api.ApiWorker
import dagger.Module
import dagger.Provides

@Module
class ApiNetworkDependenciesModule {

    @Provides
    fun apiWorker(): ApiWorker = ApiWorkerFactory.create()
}