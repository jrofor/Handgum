package com.example.roman.handgum.feature.revdetails

import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.core.baseview.BaseViewModel
import com.example.roman.handgum.core.utils.extensions.delegate
import com.example.roman.handgum.feature.revdetails.domain.RevDetailsInteractor
import com.example.roman.handgum.feature.revdetails.view.RevDetailsFragmentArgs
import com.example.roman.handgum.navigationapi.revdetails.LocalRevDetailsNavigator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author rofor
 */
internal class RevDetailsViewModel @Inject constructor(
    private val navigator: LocalRevDetailsNavigator,
    private val getRevDetails: RevDetailsInteractor
) : BaseViewModel() {

    lateinit var navArgs: RevDetailsFragmentArgs
    val liveState = MutableLiveData(createInitialState())
    private var state: RevDetailsState by liveState.delegate()
    val eventState = MutableLiveData<RevDetailsEvents>()
    private var event: RevDetailsEvents by eventState.delegate()

    override fun onCreate() {
        super.onCreate()
        getRevDetails(navArgs.url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onProgressRefresh()
            .subscribe({
                state = state.copy(
                    urlLink = it.url,
                    displayTitle = it.displayTitle,
                    mpaaRating = it.mpaaRating,
                    byline = it.byline,
                    publicationDate = it.publicationDate,
                    headline = it.headline,
                    summaryShort = it.summaryShort,
                    src = it.src,
                )
            }, {
                Timber.e(it.message)
            }).disposeOnCleared()
    }

    fun onBackPressed() {
        navigator.popBack()
    }

    fun onWebPressed(urlLink: String) {
        event = RevDetailsEvents.ShowWeb(urlLink)
    }

    internal fun createInitialState() = RevDetailsState(
        showProgress = false,
        urlLink = "",
        displayTitle = "",
        mpaaRating = "",
        byline = "",
        publicationDate = "",
        headline = "",
        summaryShort = ".",
        src = ""
    )

    private fun <T> Single<T>.onProgressRefresh() =
        doOnSubscribe { state = state.copy(showProgress = true) }.doAfterTerminate {
            state = state.copy(showProgress = false)
        }

}