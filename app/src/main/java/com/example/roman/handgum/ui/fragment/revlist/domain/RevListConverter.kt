package com.example.roman.handgum.ui.fragment.revlist.domain

import com.example.roman.handgum.apinetwork.response.MovieRevResponse
import com.example.roman.handgum.core.domain.converter.BaseConverter
import com.example.roman.handgum.core.domain.converter.BaseListConverter
import com.example.roman.handgum.database.domain.models.ReviewModel
import javax.inject.Inject


abstract class RevListConverter :
    BaseListConverter<MovieRevResponse.ReviewDTO, ReviewModel>()

class RevListConverterImpl @Inject constructor(
    override val itemConverter: RevConverter,
) : RevListConverter()

abstract class RevConverter : BaseConverter<MovieRevResponse.ReviewDTO, ReviewModel>()

class RevConverterImpl @Inject constructor() : RevConverter() {
    override fun converter(_in: MovieRevResponse.ReviewDTO) = with(_in) {
        ReviewModel(
            displayTitle = displayTitle,
            mpaaRating = mpaaRating,
            publicationDate = publicationDate,
            headline = headline,
            summaryShort = summaryShort,
            url = link.url,
            src = multimedia.src,
            width = multimedia.width,
            height = multimedia.height,
        )
    }

}