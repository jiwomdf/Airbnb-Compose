package com.programmergabut.airbnbcompose.ui.menus.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.util.ran


@Preview
@Composable
fun PreviewSearchBarScreen(){
    SearchBarScreen(
        modifier = Modifier,
        searchState = remember { mutableStateOf("") },
    ) {}
}

@Composable
fun SearchBarScreen(
    modifier: Modifier,
    shadow: Dp = 0.dp,
    searchState: MutableState<String>,
    onClickOption: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White,
            elevation = shadow
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
                        .weight(1.8F)
                        .fillMaxSize()
                        .padding(top = if (searchState.value.isNotEmpty()) 4.dp else 0.dp),
                    value = searchState.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    onValueChange = {
                        searchState.value = it
                    },
                    placeholder = {
                        Column {
                            Text(
                                text = "Where to go?",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 13.sp
                            )
                            Text(
                                text = "Anywhere • Any week • ${ran(1, 10)} guest",
                                fontSize = 10.sp
                            )
                        }
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.LightGray, CircleShape)
                            .clickable {
                                onClickOption.invoke()
                            },
                        painter = painterResource(id = R.drawable.ic_settings_white),
                        contentDescription = "setting",
                        colorFilter = ColorFilter.tint(color = Color.Black)
                    )
                }
            }
        }
    }
}