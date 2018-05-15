package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view.MoviesListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Copyright © 2017 Appster LLP. All rights reserved.
 * Created by navdeepbedi on 15/05/18.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun contributeMainActivity(): MoviesListingActivity
}
