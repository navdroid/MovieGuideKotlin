package com.droid.nav.movieguidekotlin.network

import android.arch.lifecycle.MutableLiveData
import com.droid.nav.movieguidekotlin.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by navdeepbedi on 17/05/18.
 */
@Singleton
class MovieDetailRepository @Inject
constructor(private val tmdbWebService: TmdbWebService){

    private var trailerData: MutableLiveData<VideoWrapper>? = null
    private var reviewData: MutableLiveData<ReviewsWrapper>? = null

    init {
        trailerData=MutableLiveData();
        reviewData=MutableLiveData();
    }


    fun getTrailers(id: String): MutableLiveData<VideoWrapper>? {

        tmdbWebService.trailers(id).enqueue(object : Callback<VideoWrapper> {
            override fun onResponse(call: Call<VideoWrapper>, response: Response<VideoWrapper>) {

                trailerData!!.value = response.body()


            }

            override fun onFailure(call: Call<VideoWrapper>, t: Throwable) {
                trailerData!!.value = null
            }
        })

        return trailerData;
    }

    fun getReviews(id: String): MutableLiveData<ReviewsWrapper>? {
        tmdbWebService.reviews(id).enqueue(object : Callback<ReviewsWrapper> {
            override fun onResponse(call: Call<ReviewsWrapper>, response: Response<ReviewsWrapper>) {

                reviewData!!.value = response.body()


            }

            override fun onFailure(call: Call<ReviewsWrapper>, t: Throwable) {
                reviewData!!.value = null
            }
        })

        return reviewData;
    }

}