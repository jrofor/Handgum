package com.example.roman.handgum.utils.rx

/**
 * @author rofor
 */
class ManageNull<T>(private val value: T?) {
    fun get(): T? {
        return value
    }

    companion object {
        private val NULL_REF: ManageNull<*> =
            ManageNull<Any?>(null)

        fun <T> nullM(): ManageNull<T> {
            return NULL_REF as ManageNull<T>
        }

        fun <T> of(value: T?): ManageNull<T?> {
            return ManageNull(value)
        }
    }

}