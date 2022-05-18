package com.pablodb.movieapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pablodb.movieapp.R
import com.pablodb.movieapp.app.App
import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.utils.Constantes
import kotlinx.android.synthetic.main.fragment_movie_detail.*

private const val ARG_ID = "id"

class MovieDetailFragment : Fragment() {
    private val TAG = "MovieDetailFrag"
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initElements()
    }

    fun initElements(){
        Log.i(TAG, " moviewID = $id")
        val movie = App.instance.realm!!.where(Movie::class.java)
            .equalTo("id", id).findFirst()
        Log.i(TAG, "movie = ${movie?.title} ${movie?.poster_path}")

        Glide.with( requireContext() ).load( Constantes.BASE_URL_IMAGE+movie?.poster_path)
            .into( img_poster )
        tv_popularity.text = "Popularity: ${movie?.popularity} views"
        tv_vote_avg.text = "Vote Average: ${movie?.vote_average}/10"
        tv_vote_count.text = "Vote count: ${movie?.vote_count}"
        tv_overview.text = "${movie?.overview}"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, param1)
                }
            }
    }
}