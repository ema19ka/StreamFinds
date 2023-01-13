package com.example.streamfinds.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.ui.screens.StreamsViewModel

@Composable
fun DetailsScreen(streamsViewModel: StreamsViewModel, modifier: Modifier = Modifier) {
    val movieDetails = streamsViewModel.movDet2
    println(streamsViewModel.finished)
    println("DetailScreen: $movieDetails")
    MovieDetails(movieDetails = movieDetails)


}

@Composable
fun MovieDetails(movieDetails: MovieDetails){
    Column {
        Text(text = movieDetails.title)
    }
}