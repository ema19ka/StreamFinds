package com.example.streamfinds.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.streamfinds.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    var searchInput by remember {
        mutableStateOf("")
    }
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
        BasicTextField(
            value = searchInput,
            onValueChange = { newText ->
                searchInput = newText
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFD2F3F2),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFFAAE9E6),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(all = 16.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    innerTextField()
                }
            }
        )
        Button(onClick = { navController.navigate("ResultScreen") }) {
            Text(text = "Find")


        }
    }
}
