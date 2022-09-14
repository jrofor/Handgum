package com.example.roman.handgum.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * The delegate is used to simplify saving changes inside livedata through changes to the state.
 * Representing LiveData through the state as a field.
 */
fun <T : Any> MutableLiveData<T>.delegate(): ReadWriteProperty<Any, T> =
    object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T = checkNotNull(value)
        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            this@delegate.value = value
        }
    }

/**
 * used instead of liveData.observe({ viewLifecycleOwner.lifecycle }, ::block)
 */
inline fun <reified T, LD : LiveData<T>> Fragment.observe(liveData: LD, crossinline block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { block.invoke(it) }
}