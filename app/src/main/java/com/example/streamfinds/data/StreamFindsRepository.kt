package com.example.streamfinds.data
import com.example.streamfinds.network.MovieDbAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Repository that fetch movies list from movieApi.
 */
//object bc of singleton

object StreamFindsRepository {

    private val api: MovieDbAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MovieDbAPI::class.java)
    }
}
