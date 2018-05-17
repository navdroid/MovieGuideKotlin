package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 *
 * Created by navdeepbedi on 15/05/18.
 */


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class, DetailActivityModule::class])
interface AppComponent{
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: BaseApplication)

}