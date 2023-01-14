package com.example.streamfinds.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.ui.screens.StreamsViewModel

@Composable
fun DetailsScreen(
    streamsViewModel: StreamsViewModel,
    navController: NavController,
) {
    val currentMovieId = navController.currentBackStackEntry?.arguments?.getString("movie_id")
    streamsViewModel.getMovieDetails(currentMovieId)
    val movieDetails = streamsViewModel.movDet2
    MovieDetails(movieDetails = movieDetails)
}

@Composable
fun MovieDetails(movieDetails: MovieDetails) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(25.dp)
    ) {
        Image(
            modifier = Modifier.size(500.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342/${movieDetails.posterPath}"),
            contentDescription = "poster"
        )
        Text(text = movieDetails.title, fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(12.dp))
        Row {
            Text(text = "Release Date: ")
            Text(text = movieDetails.releaseDate)
        }
        Row {
            Text(text = "Original language: ")
            Text(text = movieDetails.lang)
        }
    }
}