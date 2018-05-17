package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view.MovieDetailFragment
import com.droid.nav.movieguidekotlin.view.MoviesListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeMovieListingFragment(): MovieDetailFragment
}

