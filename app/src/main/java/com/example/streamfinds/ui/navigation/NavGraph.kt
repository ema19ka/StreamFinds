package com.example.streamfinds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.streamfinds.ui.screens.details.DetailsScreen
import com.example.streamfinds.ui.screens.StreamsViewModel
import com.example.streamfinds.ui.screens.home.HomeScreen
import com.example.streamfinds.ui.screens.result.ResultScreen


@Composable
fun NavGraph (navController: NavHostController = rememberNavController(), streamsViewModel: StreamsViewModel){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route)
    {
        composable(route = Screens.Home.route){
            HomeScreen(navController, streamsViewModel)
        }
        composable(route = Screens.Result.route){
            ResultScreen(navController, streamsViewModel)
        }
        composable(route = Screens.Details.route){
            DetailsScreen(streamsViewModel)
        }
    }
}