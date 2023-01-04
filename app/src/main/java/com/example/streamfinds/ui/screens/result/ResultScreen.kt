package com.example.streamfinds.ui.screens.result

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun ResultScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Row {
        Text(text = "ResultScreen")
        Button(onClick = { navController.navigate("details_screen") }) {
            Text(text = "Details")
        }
    }
}

