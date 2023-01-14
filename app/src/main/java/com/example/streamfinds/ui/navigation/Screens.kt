package com.example.streamfinds.ui.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object MoviesResult: Screens("movies_result")
    object ShowsResult: Screens("shows_result")
    object Details: Screens("details/{movie_id}")
}