package com.droid.nav.movieguidekotlin.network

import com.droid.nav.movieguidekotlin.model.MoviesWraper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright © 2017 Appster LLP. All rights reserved.
 * Created by navdeepbedi on 15/05/18.
 */

interface TmdbWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(@Query("page") page: Int): Call<MoviesWraper>

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMoviesOb(@Query("page") page: Int): Observable<MoviesWraper>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun highestRatedMovies(@Query("page") page: Int): Call<MoviesWraper>

    @GET("3/discover/movie?language=en&sort_by=release_date.desc")
    fun newestMovies(@Query("release_date.lte") maxReleaseDate: String, @Query("vote_count.gte") minVoteCount: Int): Call<MoviesWraper>

//    @GET("3/movie/{movieId}/videos")
//    fun trailers(@Path("movieId") movieId: String): Call<VideoWrapper>
//
//    @GET("3/movie/{movieId}/reviews")
//    fun reviews(@Path("movieId") movieId: String): Call<ReviewsWrapper>

}
