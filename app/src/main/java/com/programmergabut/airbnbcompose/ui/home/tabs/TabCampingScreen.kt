package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.home.PlacesCard

@Preview
@Composable
fun TabCampingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
    ) {
        repeat(10){
            PlacesCard(
                painter = painterResource(id = R.drawable.place1),
                contentDescription = "Android",
                title = "Abiensemal, Indonesia",
                distance = "959 kilometers away",
                date = "Oct 29 - Nov 3",
                price = 399,
                rate = 4.88F
            )
        }
    }
}
