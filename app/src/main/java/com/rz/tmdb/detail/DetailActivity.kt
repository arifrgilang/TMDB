package com.rz.tmdb.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.tmdb.BuildConfig
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private var overviewMovie: String = ""
    private var titleMovie: String = ""
    private var posterMovie: String = ""

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        val i = intent
        overviewMovie = i.getStringExtra("OVERVIEW")
        titleMovie = i.getStringExtra("TITLE")
        posterMovie = i.getStringExtra("POSTER")

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)
            //
            poster = imageView{}.lparams{
                width = dip(250)
                gravity = Gravity.CENTER
                height = dip(250)}
            //
            title = textView()
            overview = textView()
        }
        //
        Picasso.get().load(BuildConfig.URL_POSTER + posterMovie).into(poster)
        title.text =  titleMovie
        overview.text = overviewMovie
    }
}