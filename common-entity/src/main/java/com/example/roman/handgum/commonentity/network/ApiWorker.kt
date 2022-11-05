package com.example.roman.handgum.commonentity.network

import com.example.roman.handgum.commonentity.network.response.MovieRevResponse
import io.reactivex.Single

/**
 * Facade for working with the network
 * @author rofor
 */
interface ApiWorker {

    fun getMovieReviews(): Single<MovieRevResponse>
}