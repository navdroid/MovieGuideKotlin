package com.droid.nav.movieguidekotlin.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.droid.nav.movieguidekotlin.R
import com.droid.nav.movieguidekotlin.databinding.FragmentMovieDetailBinding
import com.droid.nav.movieguidekotlin.di.Injectable
import com.droid.nav.movieguidekotlin.model.Constants
import com.droid.nav.movieguidekotlin.model.Movie
import com.droid.nav.movieguidekotlin.model.Video
import com.droid.nav.movieguidekotlin.view_model.MovieDetailViewModel
import com.droid.nav.movieguidekotlin.view_model.MovieViewModel
import kotlinx.android.synthetic.main.trailers_and_reviews.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment(), Injectable {

    lateinit var binding: FragmentMovieDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var movie: Movie? = null


    companion object {

        fun getInstance(movie: Movie): MovieDetailFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val movieDetailsFragment = MovieDetailFragment()
            movieDetailsFragment.arguments = (args)
            return movieDetailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

        setToolbar()
        return binding.root
    }

    private fun setToolbar() {
        binding!!.collapsingToolbar.setContentScrimColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        binding!!.collapsingToolbar.title = getString(R.string.movie_details)
        binding!!.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
        binding!!.collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
        binding!!.collapsingToolbar.isTitleEnabled = true

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.setMovie(movie!!)
        binding!!.viewModel = viewModel

        observeViewModel(viewModel)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val movie = arguments!!.get(Constants.MOVIE) as Movie
            if (movie != null) {
                this.movie = movie

            }
        }
    }


    private fun observeViewModel(viewModel: MovieDetailViewModel) {

        viewModel.trailerData?.observe(this, Observer { trailerData ->
            run {
                if (trailerData != null) {


                    val trailers = trailerData.results;
                    if (trailers!!.isEmpty()) {

                        binding.incTrailer!!.trailersLabel.setVisibility(View.GONE)
                        binding.incTrailer!!.trailers.setVisibility(View.GONE)
                        binding.incTrailer!!.trailersContainer.setVisibility(View.GONE)

                    } else {
                        binding.incTrailer!!.trailersLabel.setVisibility(View.VISIBLE)
                        binding.incTrailer!!.trailers.setVisibility(View.VISIBLE)
                        binding.incTrailer!!.trailersContainer.setVisibility(View.VISIBLE)

                        binding.incTrailer!!.trailers.removeAllViews()
                        val inflater = activity!!.layoutInflater
                        val options = RequestOptions()
                                .placeholder(R.color.colorPrimary)
                                .centerCrop()
                                .override(150, 150)

                        for (trailer in trailers) {
                            val thumbContainer = inflater.inflate(R.layout.video, binding.incTrailer!!.trailers, false)
                            val video_thumb = thumbContainer.findViewById<ImageView>(R.id.video_thumb)
                            video_thumb.setTag(R.id.glide_tag, Video.getUrl(trailer))
                            video_thumb.requestLayout()
                            video_thumb.setOnClickListener(View.OnClickListener { view ->
                                when (view.id) {
                                    R.id.video_thumb -> {
                                        val videoUrl = view!!.getTag(R.id.glide_tag) as String
                                        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                                        startActivity(playVideoIntent)
                                    }
                                }


                            })



                            Glide.with(context!!)
                                    .load(Video.getThumbnailUrl(trailer))
                                    .apply(options)
                                    .into(video_thumb)
                            this.trailers.addView(thumbContainer)
                        }
                    }
                }
            }

        });

    }


}// Required empty public constructor

