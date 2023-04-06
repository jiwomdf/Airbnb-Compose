package com.programmergabut.airbnbcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.programmergabut.airbnbcompose.R

@Preview
@Composable
fun PreviewPlacesCard() {
    PlacesCard(
        modifier = Modifier,
        imgUrl = "",
        contentDescription = "",
        title = "Testing",
        distance = "374 kilometers away",
        date = "Oct 29 - Nov 3",
        price = 100,
        rate = 100f,
    )
}

@Composable
fun PlacesCard(
    modifier: Modifier,
    imgUrl: String,
    contentDescription: String,
    title: String,
    distance: String = "-",
    date: String = "-",
    price: Int = 0,
    rate: Float
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imgUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = debugPlaceholder(R.drawable.place1),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_love_black),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp, top = 12.dp)
                )
            }
        }
        ConstraintTitleAndRate(
            title = title,
            rate = rate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = distance,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = date,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append("$$price ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append("night")
                }
            },
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            fontSize = 12.sp
        )
    }
}


@Composable
fun ConstraintTitleAndRate(modifier: Modifier, title: String, rate: Float) {
    val titleId = "title_id"
    val starId = "star_id"
    val rateId = "rate_id"

    val constraintSet = ConstraintSet {
        val titleRef = createRefFor(titleId)
        val starRef = createRefFor(starId)
        val rateRef = createRefFor(rateId)

        constrain(titleRef) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(starRef) {
            end.linkTo(rateRef.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(rateRef) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        createVerticalChain(rateRef, chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .layoutId(titleId),
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            fontSize = 16.sp
        )
        Image(
            modifier = Modifier
                .layoutId(starId)
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_star_black), contentDescription = ""
        )
        Text(
            modifier = Modifier
                .layoutId(rateId)
                .padding(start = 4.dp),
            text = rate.toString(),
            style = TextStyle(
                color = Color.Black,
            ),
            fontSize = 14.sp
        )
    }
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }
