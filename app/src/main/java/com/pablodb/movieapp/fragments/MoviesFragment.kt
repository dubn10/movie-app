package com.pablodb.movieapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pablodb.movieapp.R
import com.pablodb.movieapp.activities.MainActivity
import com.pablodb.movieapp.adapters.MoviesAdapter
import com.pablodb.movieapp.app.App
import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.model.Popular
import com.pablodb.movieapp.model.Upcoming
import com.pablodb.movieapp.requests.Requests
import com.pablodb.movieapp.utils.Constantes
import kotlinx.android.synthetic.main.fragment_movies.*
import java.lang.Exception

class MoviesFragment : Fragment() {
    val TAG = "MoviesFragment"
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var moviesAdapter2: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            initElements()
        }catch ( e : Exception){
            Toast.makeText( requireContext(), "Error to load data", Toast.LENGTH_LONG).show()
        }
    }

    fun initElements(){
        val recyclerViewLayoutManager = LinearLayoutManager( requireContext() )
        val recyclerViewLayoutManager2 = LinearLayoutManager( requireContext() )
        rv_popular.setLayoutManager( recyclerViewLayoutManager )
        rv_upcoming.setLayoutManager( recyclerViewLayoutManager2 )

        val horizontal = LinearLayoutManager( requireContext(), LinearLayoutManager.HORIZONTAL, false);
        val horizontal2 = LinearLayoutManager( requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rv_popular.setLayoutManager(horizontal)
        rv_upcoming.setLayoutManager(horizontal2)

        Requests.getPopularMovies(){
            val popular = App.instance.realm!!.where(Popular::class.java).findFirst()
            Log.i(TAG, "token = ${popular?.results?.size}")
            if(popular != null) {
                moviesAdapter = MoviesAdapter( requireContext(), popular!!.results ){ view, movie ->
                    (requireActivity() as MainActivity).goToMovieDetail( view, movie)
                }
                rv_popular.setAdapter(moviesAdapter)
            }
        }

        Requests.getUpcomingMovies(){
            val upcoming = App.instance.realm!!.where(Upcoming::class.java).findFirst()
            Log.i(TAG, "token = ${upcoming?.results?.size}")
            if(upcoming != null) {
                moviesAdapter2 = MoviesAdapter( requireContext(), upcoming!!.results ){ view, movie ->
                    (requireActivity() as MainActivity).goToMovieDetail( view, movie)
                }
                rv_upcoming.setAdapter( moviesAdapter2 )
            }
        }

        Requests.getLatestMovies {
            val latest = App.instance.realm!!.where(Movie::class.java).equalTo("id", it).findFirst()
            Log.i(TAG, "token = ${latest}")

            if(latest!!.poster_path != null)
                Glide.with( requireContext() ).load( Constantes.BASE_URL_IMAGE+latest.poster_path)
                    .into( img_poster )
            else{
                Glide.with( requireContext() ).load( "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.algoritmodata.com.mx%2Ftag%2Fbusiness%2F&psig=AOvVaw1b-dRSaFhy2nSApIjQzdZv&ust=1652997980143000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIDm4MGH6vcCFQAAAAAdAAAAABAD")
                    .into( img_poster )
            }
            tv_title.text = latest.title
            cardview.setOnClickListener { view ->
                (requireActivity() as MainActivity).goToMovieDetail( view, latest)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}