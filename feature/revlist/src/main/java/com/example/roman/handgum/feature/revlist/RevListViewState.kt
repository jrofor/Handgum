package com.example.roman.handgum.feature.revlist

import com.example.roman.handgum.commonentity.ui.models.ReviewModel

data class RevListViewState(
    val showProgress: Boolean,
    val reviews: List<ReviewModel>,
    val missingDataNotice: Boolean,
)