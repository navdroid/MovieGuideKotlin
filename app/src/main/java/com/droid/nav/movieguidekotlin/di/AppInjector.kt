package com.droid.nav.movieguidekotlin.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.droid.nav.movieguidekotlin.BaseApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class AppInjector {

    companion object {

        fun init(mvvmApplication: BaseApplication) {
            DaggerAppComponent.builder().application(mvvmApplication)
                    .build().inject(mvvmApplication)

            mvvmApplication
                    .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                            handleActivity(activity)
                        }

                        override fun onActivityStarted(activity: Activity) {

                        }

                        override fun onActivityResumed(activity: Activity) {

                        }

                        override fun onActivityPaused(activity: Activity) {

                        }

                        override fun onActivityStopped(activity: Activity) {

                        }

                        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                        }

                        override fun onActivityDestroyed(activity: Activity) {

                        }
                    })
        }


        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                        .registerFragmentLifecycleCallbacks(
                                object : FragmentManager.FragmentLifecycleCallbacks() {
                                    override fun onFragmentCreated(fm: FragmentManager?, fragment: Fragment?,
                                                                   savedInstanceState: Bundle?) {
                                        if (fragment is Injectable) {
                                            AndroidSupportInjection.inject(fragment!!)
                                        }
                                    }
                                }, true)
            }
        }
    }

}
