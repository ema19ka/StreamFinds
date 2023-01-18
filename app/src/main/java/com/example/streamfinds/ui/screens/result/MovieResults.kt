package com.example.streamfinds.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.Movie
import com.example.streamfinds.ui.screens.StreamsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieResults(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
) {
    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "Search results") },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate("movie_search") },
                    enabled = true,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        )
        val movies = streamsViewModel.moviesList
        MoviesGridScreen(movies = movies, navController)

    }
}

/**
 * The home screen displaying photo grid.
 */
@Composable
fun MoviesGridScreen(
    movies: List<Movie>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(items = movies, key = { movie -> movie.id }) { movie ->
            MoviePosterCard(
                movie,
                navController
            )
        }
    }

}

@Composable
fun MoviePosterCard(
    movie: Movie,
    navController: NavController,
) {
    Column(
        modifier = Modifier.padding(top = 25.dp, bottom = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342/${movie.posterPath}"),
            contentDescription = "poster"
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                navController.navigate("movie_details/${movie.id}")
            }) {
            Text(text = movie.title)
        }
    }
}




