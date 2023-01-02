package com.example.streamfinds.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.model.FindDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface StreamsUiState {
    data class Success(val streams: List<FindDTO>) : StreamsUiState
    object Error : StreamsUiState
    object Loading : StreamsUiState
}

class StreamsViewModel(private val streamsRepo: StreamFindsRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var streamsUiState: StreamsUiState by mutableStateOf(StreamsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getFindItems()
    }

    fun getFindItems() {
        viewModelScope.launch {
            //streamsUiState = StreamsUiState().Loading
            streamsUiState = try {
                StreamsUiState.Success(streamsRepo.getFindItems())
            } catch (e: IOException) {
                StreamsUiState.Error
            } catch (e: HttpException) {
                StreamsUiState.Error
            }
        }
    }

    /**
     * Factory for [StreamsViewModel] that takes [StreamFindsRepository] as a dependency

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as StreamFindsRepository)
                val marsPhotosRepository = application.container.marsPhotosRepository
                StreamsViewModel(streamsRepo = streamsRepo)
            }
        }
    }
    */
}