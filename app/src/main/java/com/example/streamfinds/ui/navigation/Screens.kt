package com.example.streamfinds.ui.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Result: Screens("result_screen")
    object Details: Screens("Details_screen")
}