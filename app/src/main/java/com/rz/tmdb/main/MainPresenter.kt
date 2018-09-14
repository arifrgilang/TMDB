package com.rz.tmdb.main

import com.google.gson.Gson
import com.rz.tmdb.model.MovieResponse
import com.rz.tmdb.network.ApiRepository
import com.rz.tmdb.network.TMDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){

    fun getMovieList(){
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TMDBApi.getMovie()),
                    MovieResponse::class.java)

            uiThread {
                view.showMovieList(data.results)
            }
        }
    }
}