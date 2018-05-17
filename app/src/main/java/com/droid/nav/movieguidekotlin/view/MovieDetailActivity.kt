package com.droid.nav.movieguidekotlin.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.droid.nav.movieguidekotlin.R
import com.droid.nav.movieguidekotlin.model.Constants
import com.droid.nav.movieguidekotlin.model.Movie
import com.droid.nav.movieguidekotlin.network.Api
import com.droid.nav.movieguidekotlin.view_model.MovieDetailViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.security.AccessController.getContext

import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() , HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_movie_detail)

        setToolbar()

//

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras != null && extras.containsKey(Constants.MOVIE)) {
                val movie = extras.getParcelable<Movie>(Constants.MOVIE)
                if (movie != null) {
                    val movieDetailsFragment = MovieDetailFragment.getInstance(movie)
                    supportFragmentManager.beginTransaction().add(R.id.movie_details_container, movieDetailsFragment).commit()
                }
            }
        }


    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.movie_guide)
            supportActionBar!!.setDisplayShowTitleEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }





}
