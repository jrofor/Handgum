package com.example.roman.handgum.ui.fragment.revdetails.di

import com.example.roman.handgum.ui.fragment.revdetails.RevDetailsFragment
import dagger.Subcomponent

/**
 * @author rofor
 */
@Subcomponent(modules = [RevDetailsModule::class])
interface RevDetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RevDetailsComponent
    }

    fun inject(fragment: RevDetailsFragment)
}