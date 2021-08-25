package com.example.roman.handgum.ui.fragment.revlist.interactor

import com.example.roman.handgum.data.db.repository.RevRepository
import com.example.roman.handgum.data.networkApi.api.ApiWorker
import com.example.roman.handgum.domain.mappers.ReviewMapper
import com.example.roman.handgum.domain.models.ReviewModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListInteractor @Inject constructor(
    private val apiWorker: ApiWorker,
    private val reviewMapper: ReviewMapper,
    private val revRepository: RevRepository
) {

    fun getReviewList(): Single<List<ReviewModel>> {
        return loadReviewFromApi()
    }

    private fun loadReviewFromApi(): Single<List<ReviewModel>> {
        return apiWorker.getMovieReviews()
            .map { response -> reviewMapper.responseListToModelList(response.results) }
            .doOnSuccess { saveReviewToDb(it) }
            .flatMap { Single.fromCallable { revRepository.getAll() } }
    }

    private fun saveReviewToDb(review: List<ReviewModel>) {
        revRepository.deleteAll()
        revRepository.insert(review)
    }

}