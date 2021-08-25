package com.example.roman.handgum.di

import com.example.roman.handgum.domain.mappers.ReviewMapper
import dagger.Module
import dagger.Provides

/**
 * @author rofor
 */
@Module
class MapperModule {

    @Provides
    fun revMapper(): ReviewMapper = ReviewMapper.Companion

}