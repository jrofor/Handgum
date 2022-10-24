package com.example.roman.handgum.apinetwork.di

import com.example.roman.handgum.apinetwork.api.ApiWorker

internal object ApiWorkerFactory {
    fun create(): ApiWorker = DaggerApiNetworkComponent.factory()
        .create()
        .apiWorker
}