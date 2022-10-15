package com.example.roman.handgum.core.utils.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(T::class.java, this)

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {

        /**
         * Adding observer to the fragment lifecycle
         */
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.addObserver(
                LifecycleEventObserver { _, event ->
                    /**
                     * Clear the binding when Fragment lifecycle called the onDestroy
                     */
                    if (event == Lifecycle.Event.ON_DESTROY) binding = null
                },
            )
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding ?: takeBinding()

    @Suppress("UNCHECKED_CAST")
    private fun takeBinding(): T {
        /**
         * Checking the fragment lifecycle
         */
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }
        /**
         * get the bind method from View class and bind layout
         */
        val invoke = bindingClass.getMethod("bind", View::class.java)
            .invoke(null, fragment.requireView()) as T

        return invoke.also { this.binding = it }
    }
}
