package com.example.roman.handgum.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author rofor
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable by lazy(::CompositeDisposable)

    private var isStarted: Boolean = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            onStart()
        }
    }

    protected fun Disposable.disposeOnCleared(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected abstract fun onStart()
}