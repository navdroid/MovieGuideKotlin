package com.droid.nav.movieguidekotlin.view_model

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap
import com.droid.nav.movieguidekotlin.di.ViewModelSubComponent
import java.util.concurrent.Callable


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectViewModelFactory @Inject
constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>, Callable<ViewModel>>

    init {
        creators = ArrayMap<Class<*>, Callable<ViewModel>>()

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(MovieViewModel::class.java, Callable<ViewModel> { viewModelSubComponent.movieViewModel() })
        creators.put(MovieDetailViewModel::class.java, Callable<ViewModel> { viewModelSubComponent.movieDetailViewModel() })
        //        creators.put(ProjectListViewModel.class, () -> viewModelSubComponent.projectListViewModel());
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}