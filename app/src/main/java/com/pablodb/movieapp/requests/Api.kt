package com.pablodb.movieapp.requests

import com.pablodb.movieapp.model.Movie
import com.pablodb.movieapp.model.Popular
import com.pablodb.movieapp.model.Token
import com.pablodb.movieapp.model.Upcoming
import com.pablodb.movieapp.utils.Constantes
import com.pablodb.movieapp.utils.Constantes.Companion.BASE_API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.FormUrlEncoded


interface Api {
    @GET("authentication/token/new")
    fun getAuthToken(@QueryMap params : HashMap<String, String> ): Call<Token>?

    @GET("movie/popular")
    fun getPopularMovies(@QueryMap params : HashMap<String, String> ): Call<Popular>?

    @GET("movie/upcoming")
    fun getUpcomingMovies(@QueryMap params : HashMap<String, String> ): Call<Upcoming>?

    @GET("movie/latest")
    fun getLatestMovies(@QueryMap params : HashMap<String, String> ): Call<Movie>?


    companion object {
        const val BASE_URL = BASE_API
    }
}
