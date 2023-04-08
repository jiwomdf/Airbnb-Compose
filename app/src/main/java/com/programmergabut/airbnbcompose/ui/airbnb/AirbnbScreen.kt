package com.programmergabut.airbnbcompose.ui.airbnb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.airbnbcompose.ui.component.Divide
import com.programmergabut.airbnbcompose.ui.theme.Grey500

@Preview
@Composable
fun AirbnbScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Trips",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 40.sp,
        )
        Divider(
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "No trips booked...yet!",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
            text = "Time to dust off your bags and start planning your next adventure",
            color = Grey500
        )
        OutlinedButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onClick = { },
            shape = RoundedCornerShape(20),
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Start searching",
                color = Color.Black
            )
        }
        Divide()
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append("Can't find your reservation here? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp
                    )
                ) {
                    append("Visit The Help Center")
                }
            },
        )
    }
}