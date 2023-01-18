package com.example.streamfinds.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StreamsViewModel() : ViewModel() {


    var list = mutableListOf<Movie>()
    var showsList = mutableListOf<Show>()

    var movDet2 by mutableStateOf(MovieDetails(0, "title", "", "", "", ""))
    var showDetails by mutableStateOf(ShowDetails(0, "title", "", "", "", ""))

    var finished by mutableStateOf(false)
    var watchProviders = mutableListOf<StreamService>(StreamService("loading", "Not available on any Streaming Services"))


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

    fun getShows(query: String) {
        com.example.streamfinds.data.StreamFindsRepository.getShow(
            query,
            onSuccess = { shows ->
                showsList = shows as MutableList<Show>
            },
            onError = {
                Log.d("MainAc", "error")
            }
        )
    }

    fun getShowDetails(tvId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (tvId != null) {
                com.example.streamfinds.data.StreamFindsRepository.getShowDetails(
                    tvId.toInt(),
                    onSuccess = { showDet ->
                        showDetails = showDet
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

    fun getStreamService(tvId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (tvId != null) {
                com.example.streamfinds.data.StreamFindsRepository.getMovieWatchProviders(
                    tvId.toInt(),
                    onSuccess = { service ->
                        watchProviders = service as MutableList<StreamService>
                        //change state to indicate coroutine has finished
                    },
                    onError = {
                        watchProviders = mutableListOf<StreamService>(StreamService("loading", "Not available on any Streaming Services"))
                        Log.d("Main", "error")
                    }
                )
            }
        }
    }
}


