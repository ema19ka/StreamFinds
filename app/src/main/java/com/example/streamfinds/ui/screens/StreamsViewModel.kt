package com.example.streamfinds.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.model.SearchDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Result screen
 */
sealed interface StreamsUiState {
    data class Success(val streams: List<SearchDTO>) : StreamsUiState
    object Error : StreamsUiState
    object Loading : StreamsUiState
}

class StreamsViewModel(private val streamsRepo: StreamFindsRepository, api: String, query: String) :
    ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var streamsUiState: StreamsUiState by mutableStateOf(StreamsUiState.Loading)
        private set

    /**
     * Call getFindItems() on init so we can display status immediately.
     */
    init {
        getFindItems(api, query)
    }

    fun getFindItems(api: String, query: String) {
        viewModelScope.launch {
            //streamsUiState = StreamsUiState().Loading
            streamsUiState = try {
                StreamsUiState.Success(streamsRepo.getFindItems(api, query))
            } catch (e: IOException) {
                StreamsUiState.Error
            } catch (e: HttpException) {
                StreamsUiState.Error
            }
        }
    }


    /** Factory for [StreamsViewModel] that takes [StreamFindsRepository] as a dependency

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as StreamFindsRepository)
                val streamsRepo = application.container.streamsRepo
                StreamsViewModel(streamsRepo = streamsRepo)
            }
        }
    }*/

}