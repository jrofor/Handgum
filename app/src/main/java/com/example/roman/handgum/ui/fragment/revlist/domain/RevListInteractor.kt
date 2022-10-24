package com.example.roman.handgum.ui.fragment.revlist.domain

import com.example.roman.handgum.apinetwork.api.ApiWorker
import com.example.roman.handgum.core.utils.rx.ManageNull
import com.example.roman.handgum.database.domain.models.ReviewModel
import com.example.roman.handgum.database.repository.RevRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListInteractor @Inject constructor(
    private val apiWorker: ApiWorker,
    private val revListConverter: RevListConverter,
    private val revRepository: RevRepository
) {

    fun getReviewList(isFirstLoading: Boolean): Observable<ManageNull<List<ReviewModel>>> {
        return if (isFirstLoading) {
            Observable.zip(
                getReviewFromDb(),
                loadReviewFromApi()
            ) { t1, t2 -> dataToResult(t1, t2) }
        } else {
            loadReviewFromApi()
        }
    }

    private fun dataToResult(
        fromCache: ManageNull<List<ReviewModel>>,
        fromApi: ManageNull<List<ReviewModel>>
    ): ManageNull<List<ReviewModel>> {
        return if (fromApi.get()?.isEmpty() != true || fromCache.get() == null) fromApi
        else fromCache
    }

    private fun loadReviewFromApi(): Observable<ManageNull<List<ReviewModel>>> {
        return apiWorker.getMovieReviews()
            .map { response -> revListConverter.convert(response.results) }
            .doOnSuccess { saveReviewToDb(it) }
            .toObservable()
            .flatMap { getReviewFromDb() }
            .onErrorReturn { ManageNull(emptyList()) }
            .observeOn(Schedulers.io())
    }

    private fun saveReviewToDb(review: List<ReviewModel>) {
        revRepository.deleteAll()
        revRepository.insert(review)
    }

    private fun getReviewFromDb(): Observable<ManageNull<List<ReviewModel>>> {
        return Observable.fromCallable { ManageNull.of(revRepository.getAll()) }
    }
    //TODO add Class result instead of ManageNull<List<ReviewModel>?>
}