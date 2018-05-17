package com.droid.nav.movieguidekotlin.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author navdeep
 */
@Parcelize
class Review(
    var id: String? ,
    var author: String?,
    var content: String?,
    var url: String? ):Parcelable


