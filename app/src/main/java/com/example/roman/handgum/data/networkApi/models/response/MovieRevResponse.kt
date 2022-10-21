package com.example.roman.handgum.data.networkApi.models.response

import com.google.gson.annotations.SerializedName

/**
 * connected classes
 * [com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse]
 * [com.example.roman.handgum.database.entity.ReviewEntity]
 * @author rofor
 */
data class MovieRevResponse(
    @SerializedName("results")
    var results: List<ReviewDTO>
) {

    data class ReviewDTO(
        @SerializedName("display_title")
        var displayTitle: String = "",
        @SerializedName("mpaa_rating")
        var mpaaRating: String = "",
        @SerializedName("publication_date")
        var publicationDate: String = "",
        @SerializedName("headline")
        var headline: String = "",
        @SerializedName("summary_short")
        var summaryShort: String = "",
        var link: Link = Link(),
        var multimedia: MultiMedia = MultiMedia()
    )

    data class Link(
        @SerializedName("url")
        val url: String = ""
    )

    data class MultiMedia(
        @SerializedName("src")
        val src: String = "",
        @SerializedName("width")
        val width: String = "",
        @SerializedName("height")
        val height: String = ""
    )

}