package com.example.streamfinds.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.streamfinds.model.Movie


class StreamsViewModel() : ViewModel() {

    lateinit var moviesList: List<Movie>
    var list = mutableListOf<Movie>()
    fun isMovieInitialised() = ::moviesList.isInitialized


    fun getMovies(query: String) {
        com.example.streamfinds.data.StreamFindsRepository.getMovies(
            query,
            onSuccess = { movies ->
                Log.d("MainActivity", "Movies: $movies")
                println("Repo: $movies")
                println("idk")
                moviesList = movies
                list = movies as MutableList<Movie>
                println("Normal: $moviesList")
                println("Mut: $list")

            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }

}
/**
 * UI state for the Result screen

sealed interface StreamsUiState {

}
 */
