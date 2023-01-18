package com.example.streamfinds.data

import android.util.Log
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
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
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

    fun getShow(
        query: String,
        onSuccess: (shows: List<Show>) -> Unit,
        onError: () -> Unit
    ) {
        api.searchShow(query = query)
            .enqueue(object : Callback<GetShowsResponse> {
                override fun onResponse(
                    call: Call<GetShowsResponse>,
                    response: Response<GetShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.shows)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetShowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getShowDetails(
        id: Int,
        onSuccess: (show: ShowDetails) -> Unit,
        onError: () -> Unit

    ) {
        api.showDetails(tv_id = id)
            .enqueue(object : Callback<ShowDetails> {
                override fun onResponse(call: Call<ShowDetails>, response: Response<ShowDetails>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<ShowDetails>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieWatchProviders(
        id: Int,
        onSuccess: (streamService: List<StreamService>) -> Unit,
        onError: () -> Unit

    ) {
        api.tvWatchProviders(movie_id = id)
            .enqueue(object : Callback<GetProviders> {
                override fun onResponse(
                    call: Call<GetProviders>,
                    response: Response<GetProviders>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d("Test", response.toString())
                        Log.d("Test2", responseBody.toString())
                        if (responseBody != null) {
                            if (responseBody.providers.at != null) {
                                if (responseBody.providers.at.streamService != null) {
                                    onSuccess.invoke(responseBody.providers.at.streamService)
                                } else {
                                    onError.invoke()
                                }
                            } else {
                                onError.invoke()
                            }
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetProviders>, t: Throwable) {
                    Log.d("Main", t.toString())
                }
            })
    }
}
