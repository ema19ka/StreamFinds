package com.example.streamfinds.ui.screens.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.streamfinds.model.ShowDetails
import com.example.streamfinds.model.StreamService
import com.example.streamfinds.ui.screens.StreamsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailsScreen(
    streamsViewModel: StreamsViewModel,
    navController: NavController,
) {
    val currentShowId = navController.currentBackStackEntry?.arguments?.getString("tv_id")
    val showDetails = streamsViewModel.showDetails
    streamsViewModel.getShowDetails(currentShowId)

    val streamServices = streamsViewModel.watchProviders
    streamsViewModel.getShowStreamService(currentShowId)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = streamsViewModel.showDetails.enTitle,
                    textAlign = TextAlign.Center,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate("shows_result") },
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
        ShowDetails(showDetails = showDetails)
        if (streamServices.size != 0) {
            ShowWatchProvider(messages = streamServices)
        }
    }
}

@Composable
fun ShowDetails(showDetails: ShowDetails) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(25.dp)
    ) {
        Image(
            modifier = Modifier.size(400.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/${showDetails.posterPath}"),
            contentDescription = "poster"
        )
        Text(
            text = showDetails.title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(12.dp),
            textAlign = TextAlign.Center,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )

        )
        Text(
            text = "First aired on: ${showDetails.releaseDate}",
            fontSize = 16.sp,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )
        Text(
            text = "Original language: ${showDetails.lang}",
            fontSize = 16.sp,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )
        Text("Seasons: ${showDetails.seasons}", fontSize = 16.sp)
    }
}

@Composable
fun ShowWatchProvider(messages: List<StreamService>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        messages.forEach { message ->
            Text(text = message.name)
        }
    }
}