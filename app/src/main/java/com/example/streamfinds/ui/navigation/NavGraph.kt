package com.example.streamfinds.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavGraph (navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route)
    {
        composable(route = Screens.Result.route){
            ResultScreen(navController)
        }
        /*
        composable(route = Screens.Detail.route){
            DetailScreen()
        }*/
    }
}