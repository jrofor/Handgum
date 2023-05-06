package com.example.roman.handgum.core.baseview

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import com.example.roman.handgum.core.R
import timber.log.Timber

/**
 * @author rofor
 */
open class BaseFragment(@LayoutRes protected val layoutId: Int) : Fragment(layoutId) {

    open val navArgs: NavArgs? = null
    open val isNavigateBackVisible: Boolean = true
    private var nameLayoutFragment: String = ""

    private fun logFragmentLifecycle(eventName: String) {
        Timber.tag("ViewFragment").v("$nameLayoutFragment $eventName")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        nameLayoutFragment = resources.getResourceEntryName(layoutId)
        logFragmentLifecycle("onAttach")
        initDI()
    }

    protected open fun initDI() {
        // a place to set up a context for di injection
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logFragmentLifecycle("onViewCreated")
    }

    override fun onPause() {
        super.onPause()
        logFragmentLifecycle("onPause")
    }

    override fun onResume() {
        super.onResume()
        logFragmentLifecycle("onResume")
    }

    override fun onDestroyView() {
        logFragmentLifecycle("onDestroyView")
        super.onDestroyView()
    }

    fun openUrl(url: String) {
        try {
            startActivity(Intent(ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                R.string.intent_error_recommendation_install_browser,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}