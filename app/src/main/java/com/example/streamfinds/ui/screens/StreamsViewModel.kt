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
}

