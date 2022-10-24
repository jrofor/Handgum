package com.example.roman.handgum.ui.fragment.revlist.di

import androidx.lifecycle.ViewModel
import com.example.roman.handgum.core.di.ViewModelKey
import com.example.roman.handgum.ui.fragment.revlist.RevListViewModel
import com.example.roman.handgum.ui.fragment.revlist.domain.RevConverter
import com.example.roman.handgum.ui.fragment.revlist.domain.RevConverterImpl
import com.example.roman.handgum.ui.fragment.revlist.domain.RevListConverter
import com.example.roman.handgum.ui.fragment.revlist.domain.RevListConverterImpl
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

    @Binds
    abstract fun revConverter(revConverterImpl: RevConverterImpl): RevConverter

    @Binds
    abstract fun revConverterList(revConverterListImpl: RevListConverterImpl): RevListConverter
}