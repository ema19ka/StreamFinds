package com.example.streamfinds.data

import android.util.Log
import com.example.streamfinds.model.GetMoviesResponse
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.network.MovieDbAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getMovies(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.searchMovie(query = query)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    println(response)
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        println(responseBody)

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieDetails(
        id: Int,
        onSuccess: (movie: MovieDetails) -> Unit,
        onError: () -> Unit

    ) {
        api.movieDetails(movie_id = id)
            .enqueue(object : Callback<MovieDetails> {
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    println(response)
                    println("in onResponse")
                    if (response.isSuccessful) {
                        println("isSucc")
                        val responseBody = response.body()
                        println("respBody: $responseBody")
                        if (responseBody != null) {
                            println("is not null")
                            onSuccess.invoke(responseBody)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    println("onFail")
                    onError.invoke()
                }
            })
    }
}
