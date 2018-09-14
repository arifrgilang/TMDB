package com.rz.tmdb.network

import com.rz.tmdb.BuildConfig.API_KEY
import com.rz.tmdb.BuildConfig.BASE_URL

object TMDBApi {
    fun getMovie(): String {
        return BASE_URL + API_KEY
    }
}

