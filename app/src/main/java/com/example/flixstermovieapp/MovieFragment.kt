package com.example.flixstermovieapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MovieFragment  : Fragment(), OnListFragmentInteractionListener{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }



    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client= AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        //params["region"] = "US"

        // Using the client, perform the HTTP request
        // Using the client, perform the HTTP request
        client[
            "https://api.themoviedb.org/3/movie/top_rated",
            params,
            object : JsonHttpResponseHandler()

        {
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                // The wait for a response is over
                progressBar.hide()

                //TODO - Parse JSON into Models

                val moviesRawJSON : String = json.jsonObject.get("results").toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val models : List<Movie> =gson.fromJson(moviesRawJSON, arrayMovieType) // Fix me!
                recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                // Look for this in Logcat:
                Log.d("MovieFragment", "response successful")
            }

            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                // The wait for a response is over
                progressBar.hide()

                // If the error is not null, log it!
                t?.message?.let {
                    Log.e("MovieFragment", errorResponse)
                }
            }
        }]


    }

    /*
     * What happens when a particular book is clicked.
     */

    override fun onItemClick(item: Movie) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("poster_path",item.movieImageUrl)
        intent.putExtra("title", item.title)
        intent.putExtra("overview", item.description)
        intent.putExtra("release_date", item.releaseDate)
        intent.putExtra("original_language", item.olanguage)
        intent.putExtra("backdrop_path",item.backdrop )
      startActivity(intent)

    }

}