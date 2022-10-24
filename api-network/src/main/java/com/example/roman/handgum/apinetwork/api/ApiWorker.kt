package com.example.roman.handgum.apinetwork.api

import com.example.roman.handgum.apinetwork.response.MovieRevResponse
import io.reactivex.Single

/**
 * Facade for working with the network
 * @author rofor
 */
interface ApiWorker {

    fun getMovieReviews(): Single<MovieRevResponse>
}