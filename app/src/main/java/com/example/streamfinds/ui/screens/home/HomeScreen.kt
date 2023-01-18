package com.example.streamfinds.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.streamfinds.R
import com.example.streamfinds.ui.screens.StreamsViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    streamsViewModel: StreamsViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieSearch(navController = navController, streamsViewModel = streamsViewModel)
    }
}

@Composable
fun StreamFindsTitle() {
    Text(
        modifier = Modifier.padding(top = 60.dp, bottom = 50.dp),
        text = stringResource(R.string.app_name),
        fontSize = 32.sp,
    )
}

@Composable
fun NavBar(navController: NavController, currentIndex: Int) {
    var selectedItem by remember { mutableStateOf(currentIndex) }
    val items = listOf("Movies", "Shows")
    val routes = listOf("movie_search", "show_search")
    val icons = listOf(Icons.Default.List, Icons.Default.Star)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(routes[index])
                }
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearch(
    navController: NavController,
    streamsViewModel: StreamsViewModel,

    ) {
    var movieInput by remember {
        mutableStateOf("")
    }
    Scaffold(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                StreamFindsTitle()
                Card(
                    Modifier
                        .size(width = 400.dp, height = 300.dp)
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Search for a Movie", fontSize = 24.sp)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            BasicTextField(
                                value = movieInput,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        streamsViewModel.getMovies(movieInput)
                                        navController.navigate("movies_result")
                                    }
                                ),
                                onValueChange = { newText ->
                                    movieInput = newText
                                },
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                cursorBrush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(MaterialTheme.colorScheme.onPrimaryContainer.value),
                                        Color(MaterialTheme.colorScheme.onSecondaryContainer.value)
                                    )
                                ),
                                decorationBox = { innerTextField ->
                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 64.dp,
                                                vertical = 16.dp
                                            ) // margin left and right
                                            .fillMaxWidth()
                                            .background(
                                                color = MaterialTheme.colorScheme.primaryContainer,
                                                shape = RoundedCornerShape(size = 16.dp)
                                            )
                                            .border(
                                                width = 2.dp,
                                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                shape = RoundedCornerShape(size = 16.dp)
                                            )
                                            .padding(all = 16.dp), // inner padding
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search Icon",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        )
                                        Spacer(modifier = Modifier.width(width = 8.dp))
                                        innerTextField()
                                    }
                                }
                            )
                            Button(onClick = {
                                streamsViewModel.getMovies(movieInput)
                                navController.navigate("movies_result")
                            }) {
                                Text(text = "Search")
                            }

                        }

                    }
                }
            }
        },
        bottomBar = {
            NavBar(navController = navController, currentIndex = 0)
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSearch(
    navController: NavController,
    streamsViewModel: StreamsViewModel,

    ) {
    var showInput by remember {
        mutableStateOf("")
    }
    Scaffold(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                StreamFindsTitle()
                Card(
                    Modifier
                        .size(width = 400.dp, height = 300.dp)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Search for a Show", fontSize = 24.sp)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            BasicTextField(
                                value = showInput,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        streamsViewModel.getShows(showInput)
                                        navController.navigate("shows_result")
                                    }
                                ),
                                onValueChange = { newText ->
                                    showInput = newText
                                },
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                cursorBrush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(MaterialTheme.colorScheme.onPrimaryContainer.value),
                                        Color(MaterialTheme.colorScheme.onSecondaryContainer.value)
                                    )
                                ),
                                decorationBox = { innerTextField ->
                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 64.dp,
                                                vertical = 16.dp
                                            ) // margin left and right
                                            .fillMaxWidth()
                                            .background(
                                                color = MaterialTheme.colorScheme.primaryContainer,
                                                shape = RoundedCornerShape(size = 16.dp)
                                            )
                                            .border(
                                                width = 2.dp,
                                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                shape = RoundedCornerShape(size = 16.dp)
                                            )
                                            .padding(all = 16.dp), // inner padding
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search Icon",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        )
                                        Spacer(modifier = Modifier.width(width = 8.dp))
                                        innerTextField()
                                    }
                                }
                            )
                            Button(onClick = {
                                streamsViewModel.getShows(showInput)
                                navController.navigate("shows_result")
                            }) {
                                Text(text = "Search")
                            }

                        }
                    }
                }
            }
        },
        bottomBar = {
            NavBar(navController = navController, currentIndex = 1)
        }
    )

}


