package com.example.streamfinds.ui.screens

import android.telecom.Call
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.GetMoviesResponse
import com.example.streamfinds.network.MovieDbAPI
import kotlinx.coroutines.launch


class StreamsViewModel() : ViewModel() {


    fun getMovies(query: String) {
        com.example.streamfinds.data.StreamFindsRepository.getMovies(
            query,
            onSuccess = { movies ->
                Log.d("MainActivity", "Movies: $movies")
                println(movies)
                println("idk")

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
