package com.example.roman.handgum.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.roman.handgum.App
import com.example.roman.handgum.R
import com.example.roman.handgum.di.AppComponent
import com.example.roman.handgum.ui.activity.MainActivity

/**
 * @author rofor
 */
open class BaseFragment : Fragment() {

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    val appComponent: AppComponent
        get() = (requireActivity().application as App).appComponent

    open val titleRes: Int = R.string.app_name
    open val titleCharSequence: CharSequence by lazy { requireContext().getString(titleRes) }

    open val isNavigateBackVisible: Boolean = true

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