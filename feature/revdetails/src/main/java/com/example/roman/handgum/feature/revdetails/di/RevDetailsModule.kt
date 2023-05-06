package com.example.roman.handgum.feature.revdetails.di

import androidx.lifecycle.ViewModel
import com.example.roman.handgum.core.di.ViewModelKey
import com.example.roman.handgum.feature.revdetails.RevDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author rofor
 */
@Module
internal interface RevDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RevDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: RevDetailsViewModel): ViewModel
}