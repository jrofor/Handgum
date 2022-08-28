package com.example.roman.handgum.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.roman.handgum.utils.SingleCallHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author rofor
 */
abstract class BaseViewModel : ViewModel(), LifecycleEventObserver {

    private val compositeDisposable by lazy(::CompositeDisposable)
    private val singleCallHandler = SingleCallHandler()

    @CallSuper
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            singleCallHandler.beCalling(::onCreate)
        }
    }

    open fun onCreate() {}

    protected fun Disposable.disposeOnCleared(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}