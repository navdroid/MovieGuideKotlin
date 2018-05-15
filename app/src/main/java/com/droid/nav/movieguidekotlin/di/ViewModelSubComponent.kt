package com.droid.nav.movieguidekotlin.di

import com.droid.nav.movieguidekotlin.view_model.MovieViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun movieViewModel(): MovieViewModel
}
