package com.example.roman.handgum.ui.fragment.revdetails.di

import androidx.lifecycle.ViewModel
import com.example.roman.handgum.di.ViewModelKey
import com.example.roman.handgum.ui.fragment.revdetails.RevDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author rofor
 */
@Module
interface RevDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RevDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: RevDetailsViewModel): ViewModel
}