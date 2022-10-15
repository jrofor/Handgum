package com.example.roman.handgum.ui.fragment.revlist.di

import androidx.lifecycle.ViewModel
import com.example.roman.handgum.core.di.ViewModelKey
import com.example.roman.handgum.ui.fragment.revlist.RevListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author rofor
 */
@Module
interface RevListModule {
    @Binds
    @IntoMap
    @ViewModelKey(RevListViewModel::class)
    abstract fun bindViewModel(viewModel: RevListViewModel): ViewModel
}