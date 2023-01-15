package com.example.streamfinds.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.streamfinds.model.Show
import com.example.streamfinds.ui.screens.StreamsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowResults(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
) {
    streamsViewModel.getStreamService("1100")
    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "Search results") },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate("home") },
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
        val shows = streamsViewModel.showsList
        ShowsGridScreen(shows = shows, navController)

    }
}

/**
 * The home screen displaying photo grid.
 */
@Composable
fun ShowsGridScreen(
    shows: List<Show>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(items = shows, key = { show -> show.id }) { show ->
            ShowPosterCard(
                show,
                navController
            )
        }
    }

}

@Composable
fun ShowPosterCard(
    show: Show,
    navController: NavController,
) {
    Column(
        modifier = Modifier.padding(top = 25.dp, bottom = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342/${show.posterPath}"),
            contentDescription = "poster"
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                //navController.navigate("details/${show.id}")
            }) {
            Text(text = show.title)
        }
    }
}




