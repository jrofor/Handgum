package com.example.roman.handgum.di.network

import com.example.roman.handgum.apinetwork.di.ApiWorkerFactory
import com.example.roman.handgum.commonentity.network.ApiWorker
import dagger.Module
import dagger.Provides

@Module
class ApiNetworkDependenciesModule {

    @Provides
    fun apiWorker(): ApiWorker = ApiWorkerFactory.create()
}