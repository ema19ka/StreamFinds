package com.example.streamfinds.ui.screens.result

import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.model.SearchDTO
import com.example.streamfinds.ui.screens.StreamsUiState
import com.example.streamfinds.ui.screens.StreamsViewModel


@Composable
fun ResultScreen(
    navController: NavController,
    /*api: String,
    query: String,
    streamsUiState: StreamsUiState,*/
    modifier: Modifier = Modifier,
) {
    Text(text = "ResultScreen")
    //PosterGridScreen(streamsUiState.streams, modifier)
}


/**
 * The home screen displaying photo grid.
 */
@Composable
fun PosterGridScreen(movies: List<SearchDTO>,api: String, query: String, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        StreamsViewModel(api, query)
    }
}


@Composable
fun PosterCard(movie: SearchDTO, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(movie.id)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.loading_img),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.test),
            contentScale = ContentScale.FillBounds,
        )
    }
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.test))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.test))
        }
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.test)
        )
    }
}