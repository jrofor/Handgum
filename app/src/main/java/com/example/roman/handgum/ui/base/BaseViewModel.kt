package com.example.roman.handgum.ui.base

import androidx.lifecycle.ViewModel

/**
 * @author rofor
 */
abstract class BaseViewModel : ViewModel() {

    private var isStarted: Boolean = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            onStart()
        }
    }

    protected abstract fun onStart()
}