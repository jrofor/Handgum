package com.example.roman.handgum.data.networkApi.api

import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse

/**
 * Facade for working with the network
 * @author rofor
 */
interface ApiWorker {

    suspend fun getMovieReviews(): MovieRevResponse
}