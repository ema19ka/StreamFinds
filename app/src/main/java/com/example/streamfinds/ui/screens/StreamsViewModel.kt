package com.example.streamfinds.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.streamfinds.model.Movie


class StreamsViewModel() : ViewModel() {

    var list = mutableListOf<Movie>()


    fun getMovies(query: String) {
        com.example.streamfinds.data.StreamFindsRepository.getMovies(
            query,
            onSuccess = { movies ->
                Log.d("MainActivity", "Movies: $movies")
                list = movies as MutableList<Movie>

            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }

    fun getMovieDetails(movieId: Int) {
        println("fun")
        println(movieId)
        com.example.streamfinds.data.StreamFindsRepository.getMovieDetails(
            movieId,
            onSuccess = { movie ->
                println("bla")
                println(movie.title)
                Log.d("Test", "movie: $movie")
                /***/
            },
            onError = {
                println("error")
                Log.d("MainAc", "error")
            }
        )
    }
}

