package com.droid.nav.movieguidekotlin.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie() : Parcelable {
    var id: String? = null
    var overview: String? = null
    var release_date: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
    var title: String? = null
    var vote_average: Double = 0.toDouble()

}
