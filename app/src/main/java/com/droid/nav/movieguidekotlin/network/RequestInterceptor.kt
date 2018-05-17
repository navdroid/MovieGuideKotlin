package com.droid.nav.movieguidekotlin.network

import com.droid.nav.movieguidekotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by navdeepbedi on 15/05/18.
 */

@Singleton
class RequestInterceptor @Inject
constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}