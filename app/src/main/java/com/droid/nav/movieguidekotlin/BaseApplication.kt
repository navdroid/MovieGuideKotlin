package com.droid.nav.movieguidekotlin

import android.app.Activity
import android.app.Application
import com.droid.nav.movieguidekotlin.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Copyright © 2017 Appster LLP. All rights reserved.
 * Created by navdeepbedi on 15/05/18.
 */
class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }


}
