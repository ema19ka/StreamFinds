package com.example.streamfinds.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.streamfinds.R

@Composable
fun HomeScreen(navController: NavController,modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
            text = stringResource(R.string.app_name),
            fontSize = 28.sp,
        )
        Text(
            modifier = Modifier.padding(top = 25.dp, bottom = 15.dp),
            text = stringResource(R.string.intro),
            fontSize = 18.sp,
        )
        Button(onClick = { navController.navigate("result_screen") }) {
            Text(text = "Find Movie")

        }
    }
}
