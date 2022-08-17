package com.example.roman.handgum.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import com.example.roman.handgum.App
import com.example.roman.handgum.R
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.ui.activity.MainActivity

/**
 * @author rofor
 */
open class BaseFragment(@LayoutRes protected val layoutId: Int) : Fragment(layoutId) {

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    val appComponent: AppComponent
        get() = (requireActivity().application as App).appComponent

    open val titleRes: Int = R.string.app_name
    open val titleCharSequence: CharSequence by lazy { requireContext().getString(titleRes) }

    open val navigationArguments: NavArgs? = null
    open val isNavigateBackVisible: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDI()
    }

    protected open fun initDI() {
        // a place to set up a context for di injection
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.supportActionBar?.apply {
            title = titleCharSequence
            setDisplayHomeAsUpEnabled(isNavigateBackVisible)
            setShowHideAnimationEnabled(false)
        }
    }
}