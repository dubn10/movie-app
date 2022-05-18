package com.pablodb.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablodb.movieapp.R
import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.utils.Constantes
import io.realm.RealmObject

class MoviesAdapter(val context : Context, val dataSet : List<Movie>, val onClickListener: ( View, Movie ) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val poster : ImageView
        val content : LinearLayout

        init {
            title = view.findViewById(R.id.tv_title)
            poster = view.findViewById(R.id.img_poster)
            content = view.findViewById(R.id.ll_content)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from( p0.context )
            .inflate(R.layout.list_item_movie, p0, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = dataSet[p1]

        p0.title.text = item.title
        Glide.with( context ).load( Constantes.BASE_URL_IMAGE+item.poster_path ).into( p0.poster )

        p0.content.setOnClickListener{
            onClickListener( it, item )
        }
    }

    override fun getItemCount() = dataSet.size


}