package com.example.streamfinds.ui.screens.result

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.streamfinds.R
import com.example.streamfinds.model.Movie
import com.example.streamfinds.ui.screens.StreamsViewModel



@Composable
fun ResultScreen(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
    modifier: Modifier = Modifier,
) {
    val movies = streamsViewModel.list
    if(streamsViewModel.isMovieInitialised()){
        println(movies)
        MoviesGridScreen(movies = movies)
    }
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
            println("Movie:$movie")
        }
        println("Movies: $movies")
    }
}

@Composable
fun MoviePosterCard(movie: Movie, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),

    ) {
        Text(text = movie.title)

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(movie.posterPath)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.loading_img),
            //placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.test),
            contentScale = ContentScale.FillBounds,
        )
    }
}


