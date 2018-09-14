package com.rz.tmdb.model

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("poster_path") var poster: String,
        val id: String,
        val title: String,
        val overview: String
)
