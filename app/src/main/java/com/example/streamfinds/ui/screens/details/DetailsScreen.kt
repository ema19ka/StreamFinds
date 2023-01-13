package com.example.streamfinds.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.ui.screens.StreamsViewModel

@Composable
fun DetailsScreen(streamsViewModel: StreamsViewModel, navController: NavController, modifier: Modifier = Modifier) {
    val currentMovieId = navController.currentBackStackEntry?.arguments?.getString("movie_id")
    streamsViewModel.getMovieDetails(currentMovieId)
    val movieDetails = streamsViewModel.movDet2
    MovieDetails(movieDetails = movieDetails)
}

@Composable
fun MovieDetails(movieDetails: MovieDetails){
    Column {
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342/${movieDetails.posterPath}"),
            contentDescription = "poster"
        )
        Text(text = movieDetails.title)

    }
}