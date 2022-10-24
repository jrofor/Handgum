package com.example.roman.handgum.apinetwork.api

import com.example.roman.handgum.apinetwork.response.MovieRevResponse
import io.reactivex.Single

/**
 * Implementation Api
 * @author rofor
 */
internal class ApiWorkerImpl(
    private val api: Api
) : ApiWorker {

    override fun getMovieReviews(): Single<MovieRevResponse> {
        return api.getMovieReviews()
    }

}
