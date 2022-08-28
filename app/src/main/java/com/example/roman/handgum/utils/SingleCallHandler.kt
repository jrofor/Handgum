package com.example.roman.handgum.utils

import java.util.concurrent.atomic.AtomicBoolean

class SingleCallHandler {
    private val atomicBoolean = AtomicBoolean()

    @Synchronized
    fun beCalling(action: () -> Unit) {
        val isCalled = atomicBoolean.getAndSet(true)
        if (!isCalled) {
            action()
        }
    }
}