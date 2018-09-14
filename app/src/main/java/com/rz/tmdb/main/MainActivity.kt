package com.rz.tmdb.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.google.gson.Gson
import com.rz.tmdb.R
import com.rz.tmdb.detail.DetailActivity
import com.rz.tmdb.model.Movie
import com.rz.tmdb.network.ApiRepository
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var listMovie: RecyclerView
    private var movies: MutableList<Movie> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            listMovie = recyclerView {
                lparams (width = matchParent, height = wrapContent)
                layoutManager = GridLayoutManager(ctx, 2)
            }
        }
        adapter = MainAdapter(movies){
            startActivity<DetailActivity>(
                    "TITLE" to it.title,
                    "POSTER" to it.poster,
                    "OVERVIEW" to it.overview
            )
        }
        listMovie.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getMovieList()
    }

    override fun showMovieList(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
