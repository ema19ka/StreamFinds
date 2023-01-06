package com.example.streamfinds.ui.screens

import android.telecom.Call
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.model.Movie
import com.example.streamfinds.model.GetMoviesResponse
import com.example.streamfinds.network.MovieDbAPI
import kotlinx.coroutines.launch


class StreamsViewModel : ViewModel() {
    /*
    private val _movieList = (mutableStateListOf <GetMoviesResponse>())
    var errorMessage: String by mutableStateOf("")
    val movieList: List<GetMoviesResponse>
        get() = _movieList

    fun getMovieList(key: String, query: String) {
        viewModelScope.launch {
            val apiService = MovieDbAPI.()
            try {
                _movieList.clear()
                _movieList.addAll(apiService.findItems(key, query))

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }*/
}
/**
 * UI state for the Result screen

sealed interface StreamsUiState {

}
 */
