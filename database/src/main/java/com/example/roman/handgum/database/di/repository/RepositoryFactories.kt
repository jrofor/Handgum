package com.example.roman.handgum.database.di.repository

import com.example.roman.handgum.commonentity.db.repository.RevRepository
import com.example.roman.handgum.database.di.DaggerDatabaseComponent
import com.example.roman.handgum.database.di.DatabaseDependencies

object RevRepositoryFactory {
    fun create(dependencies: DatabaseDependencies): RevRepository =
        DaggerDatabaseComponent.factory()
            .create(dependencies)
            .revRepository
}