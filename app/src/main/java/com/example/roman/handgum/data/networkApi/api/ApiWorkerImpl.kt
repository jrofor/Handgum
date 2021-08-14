package com.example.roman.handgum.data.networkApi.api

import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse

/**
 * Implementation Api
 * @author rofor
 */
class ApiWorkerImpl(
    private val api: Api
) : ApiWorker {

    override suspend fun getMovieReviews(): MovieRevResponse {
        return api.getMovieReviews()
    }

}
