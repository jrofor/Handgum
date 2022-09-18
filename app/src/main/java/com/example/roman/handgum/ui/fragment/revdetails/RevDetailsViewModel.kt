package com.example.roman.handgum.ui.fragment.revdetails

import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.ui.base.BaseViewModel
import com.example.roman.handgum.utils.extensions.delegate
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsViewModel @Inject constructor() : BaseViewModel() {

    val liveState = MutableLiveData(createInitialState())
    var state: RevDetailsState by liveState.delegate()

    fun setUrlLink(url: String) {
        state = state.copy(urlLink = url)
    }

    private fun createInitialState() = RevDetailsState(urlLink = "")
}