package com.droid.nav.movieguidekotlin.view_model

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Bundle
import com.droid.nav.movieguidekotlin.BaseApplication
import com.droid.nav.movieguidekotlin.network.MovieRepository
import javax.inject.Inject
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.droid.nav.movieguidekotlin.model.*
import com.droid.nav.movieguidekotlin.network.Api
import com.droid.nav.movieguidekotlin.network.MovieDetailRepository
import java.security.AccessController.getContext


/**
 *
 * Created by navdeepbedi on 16/05/18.
 */
class MovieDetailViewModel @Inject
constructor(application: BaseApplication, movieRepository: MovieDetailRepository) : AndroidViewModel(application) {

    lateinit var movieLiveData: MutableLiveData<Movie>
     var trailerData: MutableLiveData<VideoWrapper>?= null
     var reviewData: MutableLiveData<ReviewsWrapper>?=null

    @Inject
    lateinit var movieRepository: MovieDetailRepository


    fun setMovie( movie: Movie) {

        movieLiveData = MutableLiveData()
        movieLiveData.value = movie

        trailerData=movieRepository.getTrailers(movie.id!!)
        reviewData=movieRepository.getReviews(movie.id!!)




    }





}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {

    Glide.with(view.context!!).load(Api.getBackdropPath(imageUrl!!)).into(view)
}