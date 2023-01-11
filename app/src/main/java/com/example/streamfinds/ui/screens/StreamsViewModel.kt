package com.example.streamfinds.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.MovieDetails


class StreamsViewModel() : ViewModel() {

    var list = mutableListOf<Movie>()

    var movDet2 by mutableStateOf(MovieDetails(1, "", "", "", ""))


    fun getMovies(query: String) {
        com.example.streamfinds.data.StreamFindsRepository.getMovies(
            query,
            onSuccess = { movies ->
                list = movies as MutableList<Movie>

            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }

    fun getMovieDetails(movieId: Int) {
        com.example.streamfinds.data.StreamFindsRepository.getMovieDetails(
            movieId,
            onSuccess = { movieDet ->
                Log.d("Test", "movie: $movieDet")
                movDet2 = movieDet
                println("in fun: $movieDet");
            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }
}

