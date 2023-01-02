package com.example.roman.handgum.core.baseview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import timber.log.Timber

/**
 * @author rofor
 */
open class BaseFragment(@LayoutRes protected val layoutId: Int) : Fragment(layoutId) {

    //open val titleRes: Int? = null
    //open val titleCharSequence: CharSequence by lazy { requireContext().getString(titleRes)}

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
        /*mainActivity.supportActionBar?.apply {
            title = titleCharSequence
            setDisplayHomeAsUpEnabled(isNavigateBackVisible)
            setShowHideAnimationEnabled(false)
        }*/
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
}