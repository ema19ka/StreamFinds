package com.example.streamfinds.network

import com.example.streamfinds.model.GetMoviesResponse
import com.example.streamfinds.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieDbAPI {
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9",
        @Query("query") query: String
    ): Call<GetMoviesResponse>

    @GET("movie")
    fun movieDetails(
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9",
        @Query("movie_id") movie_id: Int
    ): Call<Movie>
}



