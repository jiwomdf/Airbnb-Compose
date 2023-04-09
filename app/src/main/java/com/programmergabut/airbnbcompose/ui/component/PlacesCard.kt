package com.programmergabut.airbnbcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel

@Preview
@Composable
fun PreviewPlacesCard() {
    PlacesCard(
        modifier = Modifier,
        data = PlacesCardModel.PlacesCardData(
            id = "",
            imgUrl = "",
            title = "Koko-Beach-Villas, Lovina",
            dsc = "Unsplash has an amazing collection of light backgrounds, covering all different shades and styles. Our images are professionally-shot, and you can use any/all of them for free!",
            distance = "",
            date = "",
            price = 1000,
            like = 10,
            owner = "Test",
            ownerBio = "Photographer from England, sharing my digital, film + vintage slide scans",
            ownerLocation = "New Forest National Park, UK",
        ),
        onClick = {

        }
    )
}

@Composable
fun PlacesCard(
    modifier: Modifier,
    data: PlacesCardModel.PlacesCardData,
    onClick: (data: PlacesCardModel.PlacesCardData) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick.invoke(data)
            }
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.imgUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = debugPlaceholder(R.drawable.ic_img),
                    contentDescription = data.title,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_love_fill),
                    contentDescription = data.title,
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
            title = data.title,
            rate = data.like.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "${data.distance} kilometers away",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = data.date,
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
                    append("$${data.price}")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append(" night")
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
