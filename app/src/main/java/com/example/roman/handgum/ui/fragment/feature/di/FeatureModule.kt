package com.example.roman.handgum.ui.fragment.feature.di

import androidx.lifecycle.ViewModel
import com.example.roman.handgum.di.ViewModelKey
import com.example.roman.handgum.ui.fragment.feature.FeatureViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author rofor
 */
@Module
interface FeatureModule {
    @Binds
    @IntoMap
    @ViewModelKey(FeatureViewModel::class)
    abstract fun bindViewModel(viewModel: FeatureViewModel): ViewModel
}