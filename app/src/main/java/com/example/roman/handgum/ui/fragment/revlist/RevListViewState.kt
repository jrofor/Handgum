package com.example.roman.handgum.ui.fragment.revlist

import com.example.roman.handgum.domain.models.ReviewModel

data class RevListViewState(
    val showProgress: Boolean,
    val reviews: List<ReviewModel>,
    val missingDataNotice: Boolean,
)