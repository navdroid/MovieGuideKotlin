package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view.MoviesListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
     abstract fun contributeMovieListingFragment(): MoviesListingFragment
}
