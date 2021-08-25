package com.example.roman.handgum.ui.fragment.revlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.domain.models.ReviewModel
import com.example.roman.handgum.ui.base.BaseViewModel
import com.example.roman.handgum.ui.fragment.revlist.interactor.RevListInteractor
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
    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    private val _noDataLiveData = MutableLiveData<Boolean>()
    val noDataLiveData: LiveData<Boolean> = _noDataLiveData

    override fun onStart() {
        loadReview()
    }

    private fun loadReview() {
        revListInteractor.getReviewList()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { _loadingLiveData.value = true }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { _loadingLiveData.value = false }
            .subscribe({ result ->
                val listIsEmpty = result.isEmpty()
                if (!listIsEmpty) {
                    _documentLiveData.value = result
                }
                _noDataLiveData.value = listIsEmpty
            }, { error ->
                Timber.e(error)
            }).disposeOnCleared()
    }

    fun onRefresh() {
        loadReview()
    }

}