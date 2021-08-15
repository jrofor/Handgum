package com.example.roman.handgum.ui.fragment.revlist.di

import com.example.roman.handgum.ui.fragment.revlist.RevListFragment
import dagger.Subcomponent

/**
 * @author rofor
 */
@Subcomponent(modules = [RevListModule::class])
interface RevListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RevListComponent
    }

    fun inject(fragment: RevListFragment)
}