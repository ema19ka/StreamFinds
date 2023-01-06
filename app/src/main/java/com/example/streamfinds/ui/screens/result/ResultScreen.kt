package com.example.streamfinds.ui.screens.result

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.streamfinds.R
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.model.Movie
import com.example.streamfinds.ui.screens.StreamsViewModel


@Composable
fun ResultScreen(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
    modifier: Modifier = Modifier,
) {
    var searchInput by remember {
        mutableStateOf("")
    }
    Column {
        BasicTextField(
            value = searchInput,
            onValueChange = { newText ->
                searchInput = newText
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFD2F3F2),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFFAAE9E6),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(all = 16.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    innerTextField()
                }
            }
        )
        Button(onClick = {
            StreamFindsRepository.getMovies(
                searchInput,
                onSuccess = ::onMoviesFetched,
                onError = ::onError
            )
        }) {
            Text(text = "Search")

        }
        /*
        Column {
            Text(text = "ResultScreen")
            Button(onClick = { navController.navigate("details_screen") }) {
                Text(text = "Details")
            }
        }*/
        Box(modifier = modifier.fillMaxSize().padding(10.dp, 25.dp)) {
            Column{
                Image(modifier = Modifier.size(200.dp), painter = painterResource(R.drawable.loading_img), contentDescription = "loading")
                Text(text = "Loading")
            }

        }

    }

}

private fun onMoviesFetched(movies: List<Movie>) {
    Log.d("Repo", "Movies: $movies")
}

private fun onError() {
    Log.d("MainAc", "error")
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
fun MoviePosterCard(photo: Movie, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),

    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.posterPath)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.loading_img),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.test),
            contentScale = ContentScale.FillBounds,
        )
    }
}


