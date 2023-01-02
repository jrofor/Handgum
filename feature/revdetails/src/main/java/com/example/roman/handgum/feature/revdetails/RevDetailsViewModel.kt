package com.example.roman.handgum.feature.revdetails

import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.core.baseview.BaseViewModel
import com.example.roman.handgum.core.utils.extensions.delegate
import com.example.roman.handgum.navigationapi.revdetails.LocalRevDetailsNavigator
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsViewModel @Inject constructor(
    private val navigator: LocalRevDetailsNavigator
) : BaseViewModel() {

    val liveState = MutableLiveData(createInitialState())
    var state: RevDetailsState by liveState.delegate()

    fun setUrlLink(url: String) {
        state = state.copy(urlLink = url)
    }

    fun onBackPressed() {
        navigator.popBack()
    }

    private fun createInitialState() = RevDetailsState(urlLink = "")
}