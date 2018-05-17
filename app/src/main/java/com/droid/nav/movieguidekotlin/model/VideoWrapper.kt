package com.droid.nav.movieguidekotlin.model

import com.squareup.moshi.Json

/**
 * Created by navdeep on 8/20/2017.
 */

class VideoWrapper {

    @Json(name = "results")
    var results: List<Video>? = null

}
