package com.example.roman.handgum.ui.fragment.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.ui.base.BaseViewModel
import javax.inject.Inject

class FeatureViewModel @Inject constructor() : BaseViewModel() {

    var dData = "dData"

    private val _fLivaData = MutableLiveData<String>()
    val fLivaData: LiveData<String> = _fLivaData

    override fun onStart() {
        _fLivaData.value = dData
    }

}