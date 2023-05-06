package com.example.roman.handgum.feature.revdetails.domain

import com.example.roman.handgum.commonentity.db.repository.RevRepository
import com.example.roman.handgum.commonentity.ui.models.ReviewModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author rofor
 */
internal class RevDetailsInteractor @Inject constructor(
    private val revRepository: RevRepository
) {

    operator fun invoke(url: String): Single<ReviewModel> {
        return Single.fromCallable { revRepository.loadByUrl(url) }
    }
}