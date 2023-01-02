package com.example.streamfinds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.streamfinds.data.StreamFindsRepository
import com.example.streamfinds.ui.screens.ResultScreen
import com.example.streamfinds.ui.screens.StreamsViewModel


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