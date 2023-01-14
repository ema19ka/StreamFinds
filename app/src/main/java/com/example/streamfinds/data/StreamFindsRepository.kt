package com.example.streamfinds.data

import com.example.streamfinds.model.*
import com.example.streamfinds.network.MovieDbAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Repository that fetches movies list from movieApi.
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
                    if (response.isSuccessful) {
                        val responseBody = response.body()
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
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieWatchProviders(
        id: Int,
        onSuccess: (streamService: GetProviders) -> Unit,
        onError: () -> Unit

    ) {
        api.movieWatchProviders(movie_id = id)
            .enqueue(object : Callback<GetProviders> {
                override fun onResponse(call: Call<GetProviders>, response: Response<GetProviders>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                            println(response)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetProviders>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}
