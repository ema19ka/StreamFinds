package com.example.streamfinds.ui.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Result: Screens("result")
    object Details: Screens("details/{movie_id}")
}