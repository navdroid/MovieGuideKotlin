package com.droid.nav.movieguidekotlin.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.droid.nav.movieguidekotlin.R
import com.droid.nav.movieguidekotlin.databinding.MovieGridItemBinding
import com.droid.nav.movieguidekotlin.model.Constants
import com.droid.nav.movieguidekotlin.model.Movie
import com.droid.nav.movieguidekotlin.network.Api

import java.util.ArrayList


/**
 * @author navdeep
 */
class MoviesListingAdapter : RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>() {
    private var movies: MutableList<Movie>
    private var context: Context? = null

    fun addAll(movies: MutableList<Movie>?) {


        var size = 0
        if (this.movies!!.isEmpty()) {
            if (movies != null) {
                this.movies = movies
            }
        } else {
            size = this.movies!!.size

            if (movies != null) {
                this.movies!!.addAll(movies)
            }
        }
        notifyItemRangeInserted(size, this.movies!!.size)

    }

    inner class ViewHolder(var itemBinding: MovieGridItemBinding) : RecyclerView.ViewHolder(itemBinding.getRoot()), View.OnClickListener {


        var movie: Movie? = null

        override fun onClick(view: View) {


            val intent = Intent(context, MovieDetailActivity::class.java)
            val extras = Bundle()
            extras.putParcelable(Constants.MOVIE, movie)
            intent.putExtras(extras)
            context?.startActivity(intent)

        }
    }

    init {
        this.movies = ArrayList<Movie>()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemBinding = DataBindingUtil.inflate<MovieGridItemBinding>(LayoutInflater.from(context), R.layout.movie_grid_item, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(holder)
        holder.movie = movies!![position]
        holder.itemBinding.movieName.text = holder.movie!!.title

        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(context!!)
                .asBitmap()
                .load(Api.getPosterPath(holder.movie!!.poster_path))
                .apply(options)
                .into(object : BitmapImageViewTarget(holder.itemBinding.moviePoster) {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate { palette -> setBackgroundColor(palette, holder) }
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder) {
        holder.itemBinding.titleBackground.setBackgroundColor(palette.getVibrantColor(context!!
                .resources.getColor(R.color.black_translucent_60)))
    }


    override fun getItemCount(): Int {

        return movies!!.size
    }
}
