package com.droid.nav.movieguidekotlin.di

import android.arch.lifecycle.ViewModelProvider
import com.droid.nav.movieguidekotlin.BuildConfig
import com.droid.nav.movieguidekotlin.network.RequestInterceptor
import com.droid.nav.movieguidekotlin.network.TmdbWebService
import com.droid.nav.movieguidekotlin.view_model.ProjectViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * Created by navdeepbedi on 15/05/18.
 */

@Module(subcomponents = [ViewModelSubComponent::class])
class AppModule {

    @Singleton
    @Provides
     fun provideGithubService(okHttpClient: OkHttpClient): TmdbWebService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

                .create(TmdbWebService::class.java!!)
    }

    @Provides
    @Singleton
     fun provideOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build()
    }

    @Provides
    @Singleton
     fun requestInterceptor(interceptor: RequestInterceptor): Interceptor {
        return interceptor
    }

    @Singleton
    @Provides
     fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {

        return ProjectViewModelFactory(viewModelSubComponent.build())
    }

    companion object {
        val CONNECT_TIMEOUT_IN_MS = 30000
    }

}