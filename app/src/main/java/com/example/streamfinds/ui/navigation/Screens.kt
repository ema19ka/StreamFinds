package com.example.streamfinds.ui.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Result: Screens("result_screen")
    //object Detail: Screens("Detail_screen")
}