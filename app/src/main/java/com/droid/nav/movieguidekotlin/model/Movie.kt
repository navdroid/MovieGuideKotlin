package com.droid.nav.movieguidekotlin.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
        val id: String?,
        val overview: String?,
        val release_date: String?,
        val poster_path: String?,
        val backdrop_path: String?,
        val title: String?,
        val vote_average: Double?) : Parcelable


