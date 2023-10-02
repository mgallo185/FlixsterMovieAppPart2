package com.example.flixstermovieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MovieRecyclerViewAdapter(

    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)

    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {


        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movietitle) as TextView

        val mMovieDescription: TextView = mView.findViewById<View>(R.id.moviedescription) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.poster) as ImageView
        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
        }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
       holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description



        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/"+movie.movieImageUrl)
            .centerInside()
            .into(holder.mMovieImage)
        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }


}
