package com.example.streamfinds.ui.screens

sealed class Screens(val route: String) {
    object Home: Screens("../screens/home_screen")
    object Result: Screens("../screens/result_screen")
    //object Detail: Screens("Detail_screen")
}