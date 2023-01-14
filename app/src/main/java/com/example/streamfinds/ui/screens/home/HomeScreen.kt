package com.example.streamfinds.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var searchInput by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
            text = stringResource(R.string.app_name),
            fontSize = 28.sp,
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicTextField(
                value = searchInput,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        streamsViewModel.getMovies(searchInput)
                        navController.navigate("result")
                    }
                ),
                onValueChange = { newText ->
                    searchInput = newText
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 64.dp, vertical = 16.dp) // margin left and right
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
                streamsViewModel.getMovies(searchInput)
                streamsViewModel.getShows("How")
                navController.navigate("result")
            }) {
                Text(text = "Search")
            }

        }
    }
}

@Composable
fun MovieSearch(){
    Text("Movie")
}

@Composable
fun ShowSearch(){
    Text("TV")
}
