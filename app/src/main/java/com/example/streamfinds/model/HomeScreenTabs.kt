package com.example.streamfinds.model

import androidx.compose.runtime.Composable
import com.example.streamfinds.R
import com.example.streamfinds.ui.screens.home.MovieSearch
import com.example.streamfinds.ui.screens.home.ShowSearch

typealias ComposableFun = @Composable () -> Unit

sealed class HomeScreenTabs(var icon: Int, var title: String, var screen: ComposableFun) {
    object Movies : HomeScreenTabs(R.drawable.loading_img, "Movies", { MovieSearch() })
    object Shows : HomeScreenTabs(R.drawable.loading_img, "Shows", { ShowSearch() })
}