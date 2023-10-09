package com.example.flixstermovieapp
import android.os.Bundle
import android.view.RoundedCorner
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val TAG = "DetailActivity"

class DetailActivity: AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var backdropImageView: ImageView
    private lateinit var releaseTextView: TextView
    private lateinit var languageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.movie_poster)
        titleTextView = findViewById(R.id.movie_title)
        descriptionTextView = findViewById(R.id.movie_details)
       backdropImageView = findViewById(R.id.backdrop)
        releaseTextView = findViewById(R.id.movie_releasedate)
        languageTextView = findViewById(R.id.o_language)
        // TODO: Get the extra from the Intent
        val imageurl = intent.getStringExtra("poster_path").toString()

        val title= intent.getStringExtra("title").toString()
         val description ="Synothesis: " + intent.getStringExtra("overview").toString()
        val backdrop= intent.getStringExtra("backdrop_path").toString()
        val releasedate=" Original Release Date: "+intent.getStringExtra("release_date").toString()
         val languages = "Original Language: "+ intent.getStringExtra("original_language").toString()
        // TODO: Set the title, byline, and abstract information from the article

        titleTextView.text = title
        descriptionTextView.text = description
        releaseTextView.text = releasedate
        languageTextView.text = languages

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + imageurl)
            .centerInside()
            .transform(RoundedCorners(20))
            .into(mediaImageView)

        Glide.with(this)
         .load("https://image.tmdb.org/t/p/w500/" + backdrop)
            .transform(RoundedCorners(20))
         .into(backdropImageView)



    }





}
