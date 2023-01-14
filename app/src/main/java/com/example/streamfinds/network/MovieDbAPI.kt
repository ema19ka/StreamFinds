package com.example.streamfinds.network

import com.example.streamfinds.model.GetMoviesResponse
import com.example.streamfinds.model.GetProviders
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.model.StreamService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDbAPI {
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9",
        @Query("query") query: String
    ): Call<GetMoviesResponse>

    @GET("movie/{movie_id}")
    fun movieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9"
    ): Call<MovieDetails>

    @GET("movie/{movie_id}/watch/providers")
    fun movieWatchProviders(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9"
    ): Call<GetProviders>
}



