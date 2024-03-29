package com.example.roman.handgum.feature.revlist

import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.core.baseview.BaseViewModel
import com.example.roman.handgum.core.utils.extensions.delegate
import com.example.roman.handgum.feature.revlist.domain.RevListInteractor
import com.example.roman.handgum.navigationapi.revdetails.RevDetailsNavigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListViewModel @Inject constructor(
    private val revListInteractor: RevListInteractor,
    private val navigator: RevDetailsNavigator
) : BaseViewModel() {

    val liveState = MutableLiveData(createInitialState())
    private var state: RevListViewState by liveState.delegate()

    override fun onCreate() {
        super.onCreate()
        loadReview(true)
    }

    fun onRefresh() {
        loadReview(false)
    }

    fun onReviewPressed(url: String) {
        navigator.pushRevDetailsScreen(url)
    }

    private fun loadReview(isFirstLoading: Boolean) {
        revListInteractor.getReviewList(isFirstLoading)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onProgressRefresh()
            .subscribe({ result ->
                (result.get()?.isNotEmpty() ?: false).let { listIsNotEmpty ->
                    if (listIsNotEmpty) {
                        state = state.copy(reviews = result.get()!!)
                    }
                    state = state.copy(missingDataNotice = !listIsNotEmpty)
                }
            }, { error ->
                Timber.e(error)
            }).disposeOnCleared()
    }

    fun createInitialState(): RevListViewState {
        return RevListViewState(
            showProgress = false,
            reviews = mutableListOf(),
            missingDataNotice = false,
        )
    }

    private fun <T> Observable<T>.onProgressRefresh() =
        doOnSubscribe { state = state.copy(showProgress = true) }.doAfterTerminate {
            state = state.copy(showProgress = false)
        }
}