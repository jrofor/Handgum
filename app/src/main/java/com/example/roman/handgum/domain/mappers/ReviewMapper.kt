package com.example.roman.handgum.domain.mappers

import com.example.roman.handgum.data.db.entity.ReviewEntity
import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse
import com.example.roman.handgum.domain.mappers.base.BaseCommonMapper
import com.example.roman.handgum.domain.models.ReviewModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

/**
 * @author rofor
 */
@Mapper
interface ReviewMapper : BaseCommonMapper<MovieRevResponse.ReviewDTO, ReviewEntity, ReviewModel> {

    @Mappings(
        Mapping(source = "link.url", target = "url"),
        Mapping(source = "multimedia.src", target = "src"),
        Mapping(source = "multimedia.width", target = "width"),
        Mapping(source = "multimedia.height", target = "height")
    )
    override fun responseToModel(api: MovieRevResponse.ReviewDTO): ReviewModel

    @Mapping(target = "id", ignore = true)
    override fun modelToEntity(model: ReviewModel): ReviewEntity

    companion object : ReviewMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(ReviewMapper::class.java)