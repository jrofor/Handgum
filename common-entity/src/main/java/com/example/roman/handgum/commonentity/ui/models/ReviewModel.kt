package com.example.roman.handgum.commonentity.ui.models

/**
 *connected classes
 * [com.example.roman.handgum.commonentity.network.response.MovieRevResponse]
 * [com.example.roman.handgum.database.entity.ReviewEntity]
 * @author rofor
 */
data class ReviewModel(
    var displayTitle: String = "",

    var mpaaRating: String = "",

    var publicationDate: String = "",

    var headline: String = "",

    var summaryShort: String = "",

    var url: String = "",

    var src: String = "",

    var width: String = "",

    var height: String = ""
)