package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view.MovieDetailActivity
import com.droid.nav.movieguidekotlin.view.MoviesListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * Created by navdeepbedi on 16/05/18.
 */
@Module
abstract class DetailActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(DetailFragmentBuildersModule::class))
    abstract fun contributeMainActivity(): MovieDetailActivity


}