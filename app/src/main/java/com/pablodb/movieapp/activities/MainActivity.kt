package com.pablodb.movieapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.pablodb.movieapp.R
import com.pablodb.movieapp.app.App
import com.pablodb.movieapp.fragments.MovieDetailFragment
import com.pablodb.movieapp.fragments.MoviesFragment
import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.model.Token
import com.pablodb.movieapp.requests.Requests
import com.pablodb.movieapp.utils.Constantes
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElements()
        initRequests()
    }

    fun initElements(){

    }

    fun initRequests(){
        try {
            Requests.getAuthToken() {
                val token = App.instance.realm!!.where(Token::class.java).findFirst()
                Log.i(TAG, "token = ${token?.request_token}")
            }
        }catch( ignored : Exception ){}
    }

    fun goToMovieDetail(view : View, movie : Movie){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString( Constantes.ARG_ID, movie.id )
        val fmd = MovieDetailFragment()
        fmd.arguments = bundle

        transaction.replace( R.id.fl_content, fmd);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}