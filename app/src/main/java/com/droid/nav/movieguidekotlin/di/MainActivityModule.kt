package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view.MovieDetailActivity
import com.droid.nav.movieguidekotlin.view.MoviesListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * Created by navdeepbedi on 15/05/18.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun contributeMainActivity(): MoviesListingActivity


}
