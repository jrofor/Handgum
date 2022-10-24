package com.example.roman.handgum.core.domain.converter

import android.os.Looper

abstract class BaseConverter<IN, OUT> {

    fun convert(_in: IN, inMain: Boolean = false): OUT = if (threadChecker(inMain)) {
        converter(_in)
    } else {
        throw RuntimeException("Convert data on main thread ${this.javaClass.name}")
    }

    protected abstract fun converter(_in: IN): OUT

    private fun threadChecker(inMain: Boolean = false) =
        if (inMain) true else Looper.myLooper() != Looper.getMainLooper()

}

fun <IN, OUT> BaseConverter<IN, OUT>.convertSync(_in: IN): OUT {
    return convert(_in, inMain = true)
}