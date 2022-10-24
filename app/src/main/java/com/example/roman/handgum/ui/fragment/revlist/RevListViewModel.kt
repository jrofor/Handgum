package com.example.roman.handgum.ui.fragment.revlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.roman.handgum.core.baseview.BaseViewModel
import com.example.roman.handgum.core.utils.extensions.delegate
import com.example.roman.handgum.ui.fragment.revlist.domain.RevListInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListViewModel @Inject constructor(
    private val revListInteractor: RevListInteractor
) : BaseViewModel() {

    private val liveState = MutableLiveData(createInitialState())
    private var state: RevListViewState by liveState.delegate()
    var showModalProgress = liveState.map { it.showProgress }
    var reviews = liveState.map { it.reviews }
    var missingDataNotice = liveState.map { it.missingDataNotice }

    override fun onCreate() {
        super.onCreate()
        loadReview(true)
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

    fun onRefresh() {
        loadReview(false)
    }

    private fun createInitialState(): RevListViewState {
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