package com.example.roman.handgum

import android.app.Application
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.di.DaggerAppComponent

/**
 * @author rofor
 */
class App : Application() {

    val appComponent: AppComponent by lazy {
        initialComponent()
    }

    private fun initialComponent(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)
}