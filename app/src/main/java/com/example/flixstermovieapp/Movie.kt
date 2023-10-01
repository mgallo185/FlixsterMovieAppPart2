package com.example.flixstermovieapp

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField @SerializedName("description")
    var description: String? = null
}