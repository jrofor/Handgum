package com.example.roman.handgum.data.networkApi.api

import com.example.roman.handgum.data.networkApi.models.response.MovieRevResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Api calls for Retrofit
 * @author rofor
 */
interface Api {

    @GET("svc/movies/v2/reviews/all.json")
    fun getMovieReviews(): Single<MovieRevResponse>
}