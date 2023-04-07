package com.programmergabut.airbnbcompose.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.programmergabut.airbnbcompose.ui.theme.RedAirbnb

@Composable
fun AirCover(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = RedAirbnb,
                    fontSize = 28.sp
                )
            ) {
                append("air")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 28.sp
                )
            ) {
                append("cover")
            }
        },
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    )
}