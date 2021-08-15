package com.example.roman.handgum.ui.fragment.revlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roman.handgum.domain.models.ReviewModel
import com.example.roman.handgum.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListViewModel @Inject constructor() : BaseViewModel() {

    private val _documentLiveData = MutableLiveData<List<ReviewModel>>()
    val documentLiveData: LiveData<List<ReviewModel>> = _documentLiveData

    override fun onStart() {
        loadReview()
    }

    private fun loadReview() {
        val revList = mutableListOf<ReviewModel>()
        revList.add(ReviewModel(displayTitle = "title1", url = "url1"))
        revList.add(ReviewModel(displayTitle = "title2", url = "url2"))
        revList.add(ReviewModel(displayTitle = "title3", url = "url3"))
        _documentLiveData.value = revList
    }

}