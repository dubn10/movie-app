package com.pablodb.movieapp.requests

import android.util.Log
import android.widget.Toast
import com.pablodb.movieapp.app.App
import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.model.Popular
import com.pablodb.movieapp.model.Token
import com.pablodb.movieapp.model.Upcoming
import com.pablodb.movieapp.utils.Constantes
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Requests {
    companion object{
        val TAG = "Requests"

        fun getAuthToken( next : () -> Unit){
            val params = HashMap<String, String>()
            params.put( Constantes.PARAM_API_KEY, Constantes.API_KEY)

            val call: Call<Token>? = RetrofitClient.instance!!.getMyApi().getAuthToken(params)
            call?.enqueue(object : Callback<Token> {

                override fun onFailure(call: Call<Token>?, t: Throwable?) {
                    Log.i(TAG, "error = ${t}")
                    next()
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.i(TAG, "response = ${response.body()?.request_token}")
                    App.instance.realm!!.beginTransaction()
                    App.instance.realm!!.delete( Token::class.java)
                    App.instance.realm!!.copyToRealmOrUpdate( response.body()!! )
                    App.instance.realm!!.commitTransaction()

                    next()
                }
            })
        }

        fun getPopularMovies( next : () -> Unit){
            val params = HashMap<String, String>()
            params.put( Constantes.PARAM_API_KEY, Constantes.API_KEY)
            params.put( Constantes.PARAM_LANGUAGE, Constantes.LANGUAGE)
            params.put( Constantes.PARAM_PAGE, "1")

            val call: Call<Popular>? = RetrofitClient.instance!!.getMyApi().getPopularMovies(params)
            call?.enqueue(object : Callback<Popular> {

                override fun onFailure(call: Call<Popular>?, t: Throwable?) {
                    Log.i(TAG, "error = ${t}")
                    next()
                }

                override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                    Log.i(TAG, "response = ${response.body()?.results?.size}")
                    App.instance.realm!!.beginTransaction()
                    App.instance.realm!!.copyToRealmOrUpdate( response.body()!!)
                    App.instance.realm!!.commitTransaction()

                    next()
                }
            })
        }

        fun getUpcomingMovies( next : () -> Unit){
            val params = HashMap<String, String>()
            params.put( Constantes.PARAM_API_KEY, Constantes.API_KEY)
            params.put( Constantes.PARAM_LANGUAGE, Constantes.LANGUAGE)
            params.put( Constantes.PARAM_PAGE, "1")

            val call: Call<Upcoming>? = RetrofitClient.instance!!.getMyApi().getUpcomingMovies(params)
            call?.enqueue(object : Callback<Upcoming> {

                override fun onFailure(call: Call<Upcoming>?, t: Throwable?) {
                    Log.i(TAG, "error = ${t}")
                    next()
                }

                override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                    Log.i(TAG, "response = ${response.body()?.results?.size}")
                    App.instance.realm!!.beginTransaction()
                    App.instance.realm!!.copyToRealmOrUpdate( response.body()!!)
                    App.instance.realm!!.commitTransaction()

                    next()
                }
            })
        }

        fun getLatestMovies( next : ( String ) -> Unit){
            val params = HashMap<String, String>()
            params.put( Constantes.PARAM_API_KEY, Constantes.API_KEY)
            params.put( Constantes.PARAM_LANGUAGE, Constantes.LANGUAGE)

            val call: Call<Movie>? = RetrofitClient.instance!!.getMyApi().getLatestMovies(params)
            call?.enqueue(object : Callback<Movie> {

                override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                    Log.i(TAG, "error = ${t}")
                    next( "" )
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Log.i(TAG, "responseLatest = ${response.body()?.id }")
                    App.instance.realm!!.beginTransaction()
                    App.instance.realm!!.delete( Movie::class.java)
                    App.instance.realm!!.copyToRealmOrUpdate( response.body()!!)
                    App.instance.realm!!.commitTransaction()

                    next( response.body()!!.id )
                }
            })
        }


    }
}