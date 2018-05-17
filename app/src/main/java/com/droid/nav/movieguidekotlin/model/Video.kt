package com.droid.nav.movieguidekotlin.model

import android.os.Parcel
import android.os.Parcelable

import com.droid.nav.movieguidekotlin.network.Api
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * @author navdeep
 */
@Parcelize
class Video (

    private val id: String,
    private val name: String,
    private val site: String,
    @Json(name = "key")
    private val key: String,
    private val size: Int,
    private val type: String
): Parcelable{

    companion object {
        val SITE_YOUTUBE = "YouTube"


        fun getUrl(video: Video): String {
            return if (SITE_YOUTUBE.equals(video.site, ignoreCase = true)) {
                String.format(Api.YOUTUBE_VIDEO_URL, video.key
                )
            } else {
                Constants.EMPTY
            }
        }

        fun getThumbnailUrl(video: Video): String {
            return if (SITE_YOUTUBE.equals(video.site, ignoreCase = true)) {
                String.format(Api.YOUTUBE_THUMBNAIL_URL, video.key)
            } else {
                Constants.EMPTY
            }
        }
    }

}
