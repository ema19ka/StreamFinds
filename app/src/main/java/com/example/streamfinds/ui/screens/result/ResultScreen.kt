package com.example.streamfinds.ui.screens.result

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

        Column {
            Text(text = "ResultScreen")
            Button(onClick = { navController.navigate("details_screen") }) {
                Text(text = "Details")
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


