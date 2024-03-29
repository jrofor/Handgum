package com.example.roman.handgum.apinetwork.di

import com.example.roman.handgum.commonentity.network.ApiWorker

object ApiWorkerFactory {
    fun create(): ApiWorker = DaggerApiNetworkComponent.factory()
        .create()
        .apiWorker
}