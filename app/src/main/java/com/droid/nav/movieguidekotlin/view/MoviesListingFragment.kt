package com.droid.nav.movieguidekotlin.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.droid.nav.movieguidekotlin.R
import com.droid.nav.movieguidekotlin.databinding.FragmentMoviesBinding
import com.droid.nav.movieguidekotlin.di.Injectable
import com.droid.nav.movieguidekotlin.model.Movie
import com.droid.nav.movieguidekotlin.model.MoviesWraper
import com.droid.nav.movieguidekotlin.view_model.MovieViewModel

import javax.inject.Inject

class MoviesListingFragment : Fragment(), Injectable {

    private var binding: FragmentMoviesBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var moviesListingAdapter: MoviesListingAdapter? = null
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)


        val layoutManager = GridLayoutManager(activity, 2)
        moviesListingAdapter = MoviesListingAdapter()
        binding!!.moviesListing.layoutManager = layoutManager
        binding!!.moviesListing.adapter = moviesListingAdapter
        binding!!.isLoading = true

        return binding!!.root
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MovieViewModel::class.java)

        observeViewModel(viewModel)

        binding!!.moviesListing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView!!.canScrollVertically(1)) {
                    page++
                    viewModel.fetchMovieList(page)
                }
            }
        })
    }

    private fun observeViewModel(viewModel: MovieViewModel) {
        // Update the list when the data changes

//        viewModel.movieWrapper?.observe(this, Observer<MoviesWraper>{ moviesWraper ->
//                        if (moviesWraper != null) {
//
//                            binding!!.isLoading = false
//                moviesListingAdapter!!.addAll(moviesWraper.movieList)
//            }
//        });

        viewModel.movieWrapper?.observe(this, Observer { moviesWraper ->
            run {
                if (moviesWraper != null) {

                    binding!!.isLoading = false
                    moviesListingAdapter!!.addAll(moviesWraper.results as MutableList<Movie>?)
                }
            }

        });

    }


}