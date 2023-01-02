package com.example.roman.handgum.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.ActivityMainBinding
import com.example.roman.handgum.navigation.*
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var navProviderKeeper: NavProviderKeeper

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var navProvider: NavigationActionProvider = NAVIGATION_PROVIDER_STUB

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initLog()
        initNav()
    }

    private fun initNav() {
        navProvider = ActivityNavController(findMainActivityNavController(), activity = this)
        navProviderKeeper.setProvider(navProvider)
    }

    private fun findMainActivityNavController(): NavController =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController

    private fun initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }

    override fun onSupportNavigateUp() =
        findMainActivityNavController().navigateUp()

    override fun onResume() {
        super.onResume()
        navProviderKeeper.setProvider(navProvider)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findMainActivityNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        navProviderKeeper.deleteProvider(navProvider)
        navProvider = NAVIGATION_PROVIDER_STUB
    }
}