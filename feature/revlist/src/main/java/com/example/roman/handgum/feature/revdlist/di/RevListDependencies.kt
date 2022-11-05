package com.example.roman.handgum.feature.revdlist.di

import com.example.roman.handgum.commonentity.db.repository.RevRepository
import com.example.roman.handgum.commonentity.network.ApiWorker
import com.example.roman.handgum.core.di.Dependencies

interface RevListDependencies : Dependencies {
    val revRepository: RevRepository
    val apiWorker: ApiWorker
}