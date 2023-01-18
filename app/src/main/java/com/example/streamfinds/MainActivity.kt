package com.example.streamfinds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.streamfinds.ui.navigation.NavGraph
import com.example.streamfinds.ui.screens.StreamsViewModel
import com.example.streamfinds.ui.theme.StreamFindsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val streamsViewModel = StreamsViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            StreamFindsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController, streamsViewModel
                    )
                }
            }
        }
    }
}

