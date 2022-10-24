package com.example.roman.handgum.apinetwork.di

import com.example.roman.handgum.apinetwork.api.ApiWorker
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiNetworkModule::class,
    ]
)
internal interface ApiNetworkComponent {
    @Component.Factory
    interface Factory {
        fun create(): ApiNetworkComponent
    }

    val apiWorker: ApiWorker
}