package com.example.flixstermovieapp

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var movieImageUrl: String? = null

    @SerializedName("release_date")
    var releaseDate: String?= null

    @SerializedName("original_language")
    var olanguage: String?= null

    @SerializedName("backdrop_path")
    var backdrop: String?= null

}