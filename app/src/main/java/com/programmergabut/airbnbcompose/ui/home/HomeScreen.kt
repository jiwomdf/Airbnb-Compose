package com.programmergabut.airbnbcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.theme.Grey200

@Preview
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        SearchBar(
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun SearchBar(
    modifier: Modifier
) {
    var searchState by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Grey200)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .weight(0.2F)
                        .fillMaxHeight()
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    painter = painterResource(id = R.drawable.ic_search_white),
                    contentDescription = "search",
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
                TextField(
                    modifier = Modifier
                        .weight(1.6F)
                        .fillMaxSize(),
                    value = searchState,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent),
                    onValueChange = {
                        searchState = it
                    },
                    textStyle = LocalTextStyle.current.copy(color = Color.Black)
                )
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxHeight(),
                        painter = painterResource(id = R.drawable.ic_settings_white),
                        contentDescription = "setting",
                        colorFilter = ColorFilter.tint(color = Color.Black)
                    )
                }
            }
        }
    }

}