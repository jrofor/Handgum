package com.example.roman.handgum.ui.fragment.feature.di

import com.example.roman.handgum.ui.fragment.feature.FeatureFragment
import dagger.Subcomponent

/**
 * @author rofor
 */
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FeatureComponent
    }

    fun inject(fragment: FeatureFragment)
}