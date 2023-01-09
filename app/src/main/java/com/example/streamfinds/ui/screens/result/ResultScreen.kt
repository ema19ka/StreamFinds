package com.example.streamfinds.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.Movie
import com.example.streamfinds.ui.screens.StreamsViewModel



@Composable
fun ResultScreen(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
    modifier: Modifier = Modifier,
) {
    val movies = streamsViewModel.list
    MoviesGridScreen(movies = movies)
}

/**
 * The home screen displaying photo grid.
 */
@Composable
fun MoviesGridScreen(movies: List<Movie>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = movies, key = { movie -> movie.id }) { movie ->
            MoviePosterCard(movie)
        }
    }
}

@Composable
fun MoviePosterCard(movie: Movie, modifier: Modifier = Modifier) {
    Column {
        Image(modifier = Modifier.size(200.dp), painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342/${movie.posterPath}"), contentDescription = "loading")
        Text(text = movie.title)
    }
}


