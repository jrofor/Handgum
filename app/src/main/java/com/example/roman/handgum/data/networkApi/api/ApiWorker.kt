package com.example.roman.handgum.data.networkApi.api

import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse
import io.reactivex.Single

/**
 * Facade for working with the network
 * @author rofor
 */
interface ApiWorker {

    fun getMovieReviews(): Single<MovieRevResponse>
}