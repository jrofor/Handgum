package com.example.roman.handgum.database.di.mappers

import com.example.roman.handgum.database.domain.mappers.ReviewMapper
import dagger.Module
import dagger.Provides

/**
 * @author rofor
 */
@Module
class MappersModule {

    @Provides
    fun revMapper(): ReviewMapper = ReviewMapper.Companion

}