package com.example.streamfinds.network

import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.SearchDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieDbAPI {
    @GET("search/movie")
    suspend fun findItems(@Query("api_key") api_key: String, @Query("query") query: String): List<SearchDTO>

    companion object {
        var apiService: MovieDbAPI? = null
        fun getInstance(): MovieDbAPI {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(MovieDbAPI::class.java)
            }
            return apiService!!
        }
    }
}



