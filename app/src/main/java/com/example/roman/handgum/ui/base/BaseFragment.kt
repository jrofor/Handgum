package com.example.roman.handgum.ui.base

import androidx.fragment.app.Fragment
import com.example.roman.handgum.App
import com.example.roman.handgum.di.AppComponent

/**
 * @author rofor
 */
open class BaseFragment : Fragment() {

    val appComponent: AppComponent
        get() = (requireActivity().application as App).appComponent

}