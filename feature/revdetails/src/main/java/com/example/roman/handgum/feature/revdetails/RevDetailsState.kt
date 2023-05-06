package com.example.roman.handgum.feature.revdetails

internal data class RevDetailsState(
    var showProgress: Boolean,
    var urlLink: String,
    var displayTitle: String,
    var mpaaRating: String,
    var byline: String,
    var publicationDate: String,
    var headline: String,
    var summaryShort: String,
    var src: String,
)