package com.example.roman.handgum.data.networkApi.api

import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse
import io.reactivex.Single

/**
 * Implementation Api
 * @author rofor
 */
class ApiWorkerImpl(
    private val api: Api
) : ApiWorker {

    override fun getMovieReviews(): Single<MovieRevResponse> {
        return api.getMovieReviews()
    }

}
