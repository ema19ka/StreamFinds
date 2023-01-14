package com.example.streamfinds.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StreamsViewModel() : ViewModel() {



    var list = mutableListOf<Movie>()

    var movDet2 by mutableStateOf(MovieDetails(0, "title", "", "", ""))

    var finished by mutableStateOf(false)


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

    fun getMovieDetails(movieId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movieId != null) {
                com.example.streamfinds.data.StreamFindsRepository.getMovieDetails(
                    movieId.toInt(),
                    onSuccess = { movieDet ->
                        movDet2 = movieDet
                        //change state to indicate coroutine has finished
                        finished = true
                    },
                    onError = {
                        Log.d("MainAc", "error")
                    }
                )
            }
        }
    }
}

