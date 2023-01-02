package com.example.roman.handgum.ui.activity.di


import com.example.roman.handgum.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author rofor
 */
@Module
internal abstract class ActivityBuilderModule() {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun providesMainActivityInjector(): MainActivity

}