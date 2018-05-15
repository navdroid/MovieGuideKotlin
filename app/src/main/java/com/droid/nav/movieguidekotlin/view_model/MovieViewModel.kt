package com.droid.nav.movieguidekotlin.view_model

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.droid.nav.movieguidekotlin.BaseApplication
import com.droid.nav.movieguidekotlin.model.MoviesWraper
import com.droid.nav.movieguidekotlin.network.MovieRepository


import javax.inject.Inject

/**
 * Created by navdeepbedi on 21/04/18.
 */

class MovieViewModel @Inject
constructor(application: BaseApplication, movieRepository: MovieRepository) : AndroidViewModel(application) {

    private var movieWraperLiveData: MutableLiveData<MoviesWraper>? = null

    @Inject
    lateinit var movieRepository: MovieRepository


    val movieWrapper: LiveData<MoviesWraper>?
        get() = movieWraperLiveData

    init {

        movieWraperLiveData = movieRepository.fetchMovies(1)
    }

    fun fetchMovieList(page: Int) {
        movieWraperLiveData = movieRepository!!.fetchMovies(page)

    }
}