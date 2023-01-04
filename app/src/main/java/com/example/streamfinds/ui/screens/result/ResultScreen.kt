package com.example.streamfinds.ui.screens.result

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.streamfinds.ui.screens.StreamsViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
    modifier: Modifier = Modifier,
) {
    Row {
        LaunchedEffect(Unit, block = {
            streamsViewModel.getMovieList(
                key = "4e3418e89befff40b8dfab831c11e2d9",
                query = "Palm Springs"
            )
        })

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row {
                            Text("Movies")
                        }
                    })
            },
            content = {
                if (streamsViewModel.errorMessage.isEmpty()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Test")
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(streamsViewModel.movieList) { movie ->
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                        ) {
                                            Text(
                                                "not working",
                                                //movie.results.title,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                    }
                                    Divider()
                                }
                            }
                        }
                    }
                } else {
                    println(streamsViewModel.errorMessage)
                    Text(streamsViewModel.errorMessage)
                }
            }
        )
    }/*
    Column {
        //Text(text = "ResultScreen")
        Button(onClick = { navController.navigate("details_screen") }) {
            Text(text = "Details")
        }
    }*/

}

