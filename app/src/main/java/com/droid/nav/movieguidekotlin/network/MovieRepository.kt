package com.droid.nav.movieguidekotlin.network

import android.arch.lifecycle.MutableLiveData
import com.droid.nav.movieguidekotlin.model.MoviesWraper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Copyright © 2017 Appster LLP. All rights reserved.
 * Created by navdeepbedi on 15/05/18.
 */

@Singleton
class MovieRepository @Inject
constructor(private val tmdbWebService: TmdbWebService) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private var data: MutableLiveData<MoviesWraper>? = null

    fun initMovieList(): MutableLiveData<MoviesWraper> {
        data = MutableLiveData<MoviesWraper>()
        return data as MutableLiveData<MoviesWraper>
    }


    fun fetchMovies(page: Int): MutableLiveData<MoviesWraper>? {

        if (page == 1)
            initMovieList()


        tmdbWebService.popularMovies(page).enqueue(object : Callback<MoviesWraper> {
            override fun onResponse(call: Call<MoviesWraper>, response: Response<MoviesWraper>) {

                data!!.value = response.body()


            }

            override fun onFailure(call: Call<MoviesWraper>, t: Throwable) {
                data!!.value = null
            }
        })

        return data
    }

    companion object {
        private val NEWEST_MIN_VOTE_COUNT = 50
    }


}
