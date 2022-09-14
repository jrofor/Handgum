package com.example.roman.handgum.ui.fragment.revlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.roman.handgum.domain.models.ReviewModel
import com.example.roman.handgum.ui.base.BaseViewModel
import com.example.roman.handgum.ui.fragment.revlist.interactor.RevListInteractor
import com.example.roman.handgum.utils.extensions.delegate
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

    private val _documentLiveData = MutableLiveData<List<ReviewModel>>()
    val documentLiveData: LiveData<List<ReviewModel>> = _documentLiveData
    private val _noDataLiveData = MutableLiveData<Boolean>()
    val noDataLiveData: LiveData<Boolean> = _noDataLiveData

    private val liveState = MutableLiveData(createInitialState())
    private var state: RevListViewState by liveState.delegate()
    var showModalProgress = liveState.map { it.showProgress }

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
                val listIsEmpty = result.get()?.isEmpty() ?: false
                if (!listIsEmpty) {
                    _documentLiveData.value = result.get()
                }
                _noDataLiveData.value = listIsEmpty
            }, { error ->
                Timber.e(error)
            }).disposeOnCleared()
    }

    fun onRefresh() {
        loadReview(false)
    }

    private fun createInitialState(): RevListViewState {
        return RevListViewState(
            showProgress = false
        )
    }

    private fun <T> Observable<T>.onProgressRefresh() =
        doOnSubscribe { state = state.copy(showProgress = true) }
            .doAfterTerminate { state = state.copy(showProgress = false) }
}