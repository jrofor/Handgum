package com.example.roman.handgum.ui.fragment.revdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsViewModel @Inject constructor() : BaseViewModel() {

    var urlLink = ""

    private val _urlLinkLivaData = MutableLiveData<String>()
    val urlLinkLivaData: LiveData<String> = _urlLinkLivaData

    override fun onCreate() {
        _urlLinkLivaData.value = urlLink
    }

}