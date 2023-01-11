package com.example.streamfinds.ui.screens

import android.util.Log

import androidx.lifecycle.ViewModel
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.MovieDetails


class StreamsViewModel() : ViewModel() {

    var list = mutableListOf<Movie>()
    var details = MovieDetails(1,  "title", "path", "date", "lang")


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
            onSuccess = { movieDet ->
                Log.d("Test", "movie: $movieDet")
                /***/
                details = movieDet
                println("details: $details");
            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }
}

