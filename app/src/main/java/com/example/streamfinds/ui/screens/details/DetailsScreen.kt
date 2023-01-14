package com.example.streamfinds.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.MovieDetails
import com.example.streamfinds.ui.screens.StreamsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    streamsViewModel: StreamsViewModel,
    navController: NavController,
) {
    val currentMovieId = navController.currentBackStackEntry?.arguments?.getString("movie_id")
    val movieDetails = streamsViewModel.movDet2
    streamsViewModel.getMovieDetails(currentMovieId)
    //streamsViewModel.getStreamService(currentMovieId)

    Column{
        CenterAlignedTopAppBar(
            title = { Text(text = streamsViewModel.movDet2.enTitle) },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate("result") },
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
        MovieDetails(movieDetails = movieDetails)
    }

}

@Composable
fun MovieDetails(movieDetails: MovieDetails) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(25.dp)
    ) {
        Image(
            modifier = Modifier.size(500.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/${movieDetails.posterPath}"),
            contentDescription = "poster"
        )
        Text(
            text = movieDetails.title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(12.dp)
        )
        Row {
            Text(
                text = "Release Date: ${movieDetails.releaseDate}",
                fontSize = 16.sp,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 2.5.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
        }
        Row {
            Text(text = "Original language: ${movieDetails.lang}", fontSize = 16.sp)
        }
    }
}