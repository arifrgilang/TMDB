package com.rz.tmdb.main

import com.rz.tmdb.model.Movie

interface MainView{
    fun showMovieList(data: List<Movie>)
}