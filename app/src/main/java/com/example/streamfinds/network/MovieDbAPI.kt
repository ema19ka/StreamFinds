package com.example.streamfinds.network

import com.example.streamfinds.model.*
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

    @GET("search/tv")
    fun searchShow(
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9",
        @Query("query") query: String
    ): Call<GetShowsResponse>

    @GET("tv/{tv_id}")
    fun showDetails(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9"
    ): Call<ShowDetails>

    @GET("movie/{movie_id}/watch/providers")
    fun movieWatchProviders(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = "4e3418e89befff40b8dfab831c11e2d9"
    ): Call<GetProviders>
}



