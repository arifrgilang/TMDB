package com.rz.tmdb.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.tmdb.BuildConfig.URL_POSTER
import com.rz.tmdb.R
import com.rz.tmdb.model.Movie
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainAdapter(private val results: List<Movie>, private val listener: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        return MovieViewHolder(MovieUI()
                .createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.bindItem(results[p1], listener)
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val moviePoster: ImageView = view.find(R.id.movie_poster)
    private val movieTitle: TextView = view.find(R.id.movie_title)

    fun bindItem(movies: Movie, listener: (Movie) -> Unit){
        Picasso.get().load(URL_POSTER + movies.poster).into(moviePoster)
        movieTitle.text = movies.title

        moviePoster.onClick {
            listener(movies)
        }
    }
}

class MovieUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(5)
                orientation = LinearLayout.VERTICAL

                imageView {
                    id = R.id.movie_poster
                }.lparams{
                    height = dip(250)
                    width = wrapContent
                }

                textView {
                    id = R.id.movie_title
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}