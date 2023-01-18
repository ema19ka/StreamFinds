package com.example.streamfinds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.streamfinds.ui.screens.details.MovieDetailsScreen
import com.example.streamfinds.ui.screens.StreamsViewModel
import com.example.streamfinds.ui.screens.details.ShowDetailsScreen
import com.example.streamfinds.ui.screens.home.HomeScreen
import com.example.streamfinds.ui.screens.home.MovieSearch
import com.example.streamfinds.ui.screens.home.ShowSearch
import com.example.streamfinds.ui.screens.result.MovieResults
import com.example.streamfinds.ui.screens.result.ShowResults


@Composable
fun NavGraph (navController: NavHostController = rememberNavController(), streamsViewModel: StreamsViewModel){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route)
    {
        composable(route = Screens.Home.route){
            HomeScreen(navController, streamsViewModel)
        }
        composable(route = Screens.MovieSearch.route){
            MovieSearch(navController, streamsViewModel)
        }
        composable(route = Screens.ShowSearch.route){
            ShowSearch(navController, streamsViewModel)
        }
        composable(route = Screens.MoviesResult.route){
            MovieResults(navController, streamsViewModel)
        }
        composable(route = Screens.ShowsResult.route){
            ShowResults(navController, streamsViewModel)
        }
        composable(route = Screens.MovieDetails.route){ backStackEntry ->
            val movie_id = backStackEntry.arguments?.getString("movie_id")
            requireNotNull(movie_id) { "MovieId parameter wasn't found. Please make sure it's set!" }
            MovieDetailsScreen(streamsViewModel, navController)
        }
        composable(route = Screens.ShowDetails.route){ backStackEntry ->
            val show_id = backStackEntry.arguments?.getString("tv_id")
            requireNotNull(show_id) { "ShowId parameter wasn't found. Please make sure it's set!" }
            ShowDetailsScreen(streamsViewModel, navController)
        }
    }
}