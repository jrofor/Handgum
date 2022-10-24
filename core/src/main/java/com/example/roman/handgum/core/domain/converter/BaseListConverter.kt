package com.example.roman.handgum.core.domain.converter

import timber.log.Timber

abstract class BaseListConverter<IN, OUT> : BaseConverter<List<IN>, List<OUT>>() {

    protected abstract val itemConverter: BaseConverter<IN, OUT>

    override fun converter(_in: List<IN>): List<OUT> = _in.mapNotNull {
        try {
            itemConverter.convert(it)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}
