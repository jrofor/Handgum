package com.example.roman.handgum.database.di.mappers

import com.example.roman.handgum.database.di.DaggerDatabaseComponent
import com.example.roman.handgum.database.di.DatabaseDependencies
import com.example.roman.handgum.database.domain.mappers.ReviewMapper

object RevMapperFactory {
    fun create(dependencies: DatabaseDependencies): ReviewMapper = DaggerDatabaseComponent.factory()
        .create(dependencies)
        .revMapper
}