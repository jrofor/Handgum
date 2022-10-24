package com.example.roman.handgum.apinetwork.api

import com.example.roman.handgum.apinetwork.response.MovieRevResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Api calls for Retrofit
 * @author rofor
 */
internal interface Api {

    @GET("svc/movies/v2/reviews/all.json")
    fun getMovieReviews(): Single<MovieRevResponse>
}