package com.example.roman.handgum

import android.app.Application
import com.example.roman.handgum.core.di.DepsMap
import com.example.roman.handgum.core.di.HasDependencies
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.di.DaggerAppComponent
import javax.inject.Inject

/**
 * @author rofor
 */
class App : Application(), HasDependencies {

    @Inject
    override lateinit var depsMap: DepsMap

/*    val appComponent: AppComponent by lazy {
        initialComponent()
    }*/

    override fun onCreate() {
        super.onCreate()
        initialComponent().inject(this)
    }

    private fun initialComponent(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)
}