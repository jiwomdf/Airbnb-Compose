package com.programmergabut.airbnbcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview
@Composable
fun PlacesCardShimmer() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .customShimmer()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(Color.LightGray)
                )
            }
        }
        Box(
            modifier = Modifier.padding(top = 16.dp)
                .width(100.dp)
                .height(30.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
        )
        Box(
            modifier = Modifier.padding(top = 8.dp)
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
        )
        Box(
            modifier = Modifier.padding(top = 8.dp)
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
        )
    }
}