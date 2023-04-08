package com.programmergabut.airbnbcompose.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Divide() {
    Divider(
        modifier = Modifier.padding(16.dp),
        color = Color.LightGray,
        thickness = 1.dp,
    )
}