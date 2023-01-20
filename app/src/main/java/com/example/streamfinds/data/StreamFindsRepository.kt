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
        val retrofit = createRetrofit()
        api = retrofit.create(MovieDbAPI::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun <T> createCallback(onSuccess: (T) -> Unit, onError: () -> Unit) : Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onError()
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError()
            }
        }
    }

    fun getMovies(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.searchMovie(query = query)
            .enqueue(createCallback(onSuccess = {response -> onSuccess(response.movies)}, onError))
    }

    fun getMovieDetails(
        id: Int,
        onSuccess: (movie: MovieDetails) -> Unit,
        onError: () -> Unit

    ) {
        api.movieDetails(movie_id = id)
            .enqueue(createCallback(onSuccess = {response -> onSuccess(response)}, onError))
    }

    fun getShow(
        query: String,
        onSuccess: (shows: List<Show>) -> Unit,
        onError: () -> Unit
    ) {
        api.searchShow(query = query)
            .enqueue(createCallback(onSuccess = {response -> onSuccess(response.shows)}, onError))
    }

    fun getShowDetails(
        id: Int,
        onSuccess: (show: ShowDetails) -> Unit,
        onError: () -> Unit

    ) {
        api.showDetails(show_id = id)
            .enqueue(createCallback(onSuccess = {response -> onSuccess(response)}, onError))
    }

    fun getMovieWatchProviders(
        id: Int,
        onSuccess: (streamService: List<StreamService>) -> Unit,
        onError: () -> Unit

    ) {
        api.movieWatchProviders(movie_id = id)
            .enqueue(object : Callback<GetProvidersCH> {
                override fun onResponse(
                    call: Call<GetProvidersCH>,
                    response: Response<GetProvidersCH>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null && responseBody.providers.ch != null && responseBody.providers.ch.streamService != null) {
                        onSuccess.invoke(responseBody.providers.ch.streamService)
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetProvidersCH>, t: Throwable) {
                    Log.d("Main", "error")
                }
            })
    }

    fun getShowWatchProviders(
        id: Int,
        onSuccess: (streamService: List<StreamService>) -> Unit,
        onError: () -> Unit

    ) {
        api.showWatchProviders(show_id = id)
            .enqueue(object : Callback<GetProvidersAT> {
                override fun onResponse(
                    call: Call<GetProvidersAT>,
                    response: Response<GetProvidersAT>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null && responseBody.providers.at != null && responseBody.providers.at.streamService != null) {
                        onSuccess.invoke(responseBody.providers.at.streamService)
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetProvidersAT>, t: Throwable) {
                    Log.d("Main", "error")
                }
            })
    }
}
