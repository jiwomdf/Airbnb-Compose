package com.programmergabut.airbnbcompose.ui.placedetail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.domain.model.FeatureModel
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.ui.FakePlacesViewModel
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.component.AirCover
import com.programmergabut.airbnbcompose.ui.component.Divide
import com.programmergabut.airbnbcompose.ui.component.debugPlaceholder
import com.programmergabut.airbnbcompose.ui.theme.Grey200
import com.programmergabut.airbnbcompose.ui.theme.Grey500
import com.programmergabut.airbnbcompose.ui.theme.RedAirbnb
import com.programmergabut.airbnbcompose.util.ran
import com.programmergabut.airbnbcompose.util.toString
import java.util.Date


@Preview
@Composable
fun PreviewPlaceDetail() {
    PlaceDetailScreen(
        modifier = Modifier.fillMaxWidth(),
        navController = rememberNavController(),
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
        viewModel = FakePlacesViewModel(),
        scaffoldState = rememberScaffoldState()
    )
}

@Composable
fun PlaceDetailScreen(
    modifier: Modifier,
    navController: NavController,
    scaffoldState: ScaffoldState,
    data: PlacesCardModel.PlacesCardData?,
    viewModel: IPlacesViewModel
) {

    val context = LocalContext.current
    val features = viewModel.getFeatures()

    ConstraintLayout(
        modifier = modifier
    ) {
        val (cContent, cReserve) = createRefs()

        Column(
            modifier = Modifier
                .constrainAs(cContent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp)
        ) {
            ConstraintTitleAndRate(
                context = context,
                imgUrl = data?.imgUrl ?: "",
                navController = navController,
                scaffoldState = scaffoldState
            )
            PlacesTitle(data)
            Divide()
            PlacesHostedBy(data?.owner ?: "-")
            Divide()
            Column(
                modifier = Modifier
                    .padding(top = 2.dp)
            ) {
                for (i in features.indices) {
                    val feature = features[i]
                    PlaceFeature(feature, i)
                }
            }
            Divide()
            AirCover(modifier = Modifier.padding(start = 16.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 48.dp),
                color = Grey500,
                text = "Every booking includes free protections from Host cancellations, listing inaccuracies, and other issues like trouble checking in"
            )
        }
        ReserveButton(
            modifier = Modifier
                .constrainAs(cReserve) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .background(Color.White),
            price = data?.price ?: 0
        )

    }
}

@Composable
fun ConstraintTitleAndRate(
    context: Context,
    imgUrl: String,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val (showSnackBar, setShowSnackBar) = remember {
        mutableStateOf(false)
    }
    if (showSnackBar) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = "Places favorite",
                actionLabel = "undo"
            )
            when (result) {
                SnackbarResult.Dismissed -> {
                    setShowSnackBar(false)
                }
                SnackbarResult.ActionPerformed -> {
                    setShowSnackBar(false)
                    // perform action here
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (aiImg, boxBack, boxLike, boxShare, tPage) = createRefs()

        AsyncImage(
            modifier = Modifier
                .constrainAs(aiImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgUrl)
                .crossfade(true)
                .build(),
            placeholder = debugPlaceholder(R.drawable.ic_img),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Box(modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .constrainAs(boxBack) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            }
            .clickable {
                navController.popBackStack()
            }
        ) {
            Image(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = ""
            )
        }
        Box(modifier = Modifier
            .padding(end = 8.dp, top = 8.dp)
            .constrainAs(boxLike) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            .clickable {
                setShowSnackBar(true)
            }
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .width(35.dp)
                    .height(35.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_love),
                colorFilter = ColorFilter.tint(color = Color.Black),
                contentDescription = ""
            )
        }
        Box(modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .constrainAs(boxShare) {
                top.linkTo(parent.top)
                end.linkTo(boxLike.start)
                width = Dimension.fillToConstraints
            }
            .clickable {
                Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_SUBJECT, "Airbnb Clone")
                    it.putExtra(
                        Intent.EXTRA_TEXT,
                        "Airbnb Clone github: https://github.com/jiwomdf"
                    )
                    context.startActivity(Intent.createChooser(it, "Choose the app"))
                }
            }
        ) {
            Image(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_ios_share),
                contentDescription = ""
            )
        }
        Box(modifier = Modifier
            .padding(end = 8.dp, bottom = 8.dp)
            .constrainAs(tPage) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(40))
                    .padding(start = 6.dp, end = 6.dp),
                text = "1/1",
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
        }

    }

}

@Composable
fun PlacesTitle(data: PlacesCardModel.PlacesCardData?) {
    Text(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp),
        text = data?.title ?: "-",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    )
    Row(
        modifier = Modifier.padding(start = 16.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(16.dp)
                .height(16.dp),
            painter = painterResource(id = R.drawable.ic_star_black),
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = (data?.like ?: 0).toString(),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = " • ${data?.like ?: 0} reviews"
        )
    }
    if (!data?.ownerLocation.isNullOrEmpty()) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 2.dp),
            text = data?.ownerLocation ?: ""
        )
    }
    if (!data?.dsc.isNullOrEmpty()) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 2.dp, end = 16.dp),
            text = data?.dsc ?: "",
            color = Grey500
        )
    }
}

@Composable
fun PlacesHostedBy(owner: String) {
    Text(
        modifier = Modifier
            .padding(top = 10.dp, start = 16.dp),
        text = "Entire place hosted by $owner",
        color = Color.Black,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier.padding(top = 10.dp, start = 16.dp),
        color = Grey500,
        text = "${ran(1, 5)} guests • ${ran(1, 5)} bedroom • ${ran(1, 5)} bed • ${ran(1, 5)} bath"
    )
}

@Composable
private fun PlaceFeature(feature: FeatureModel, idx: Int) {
    Row(
        modifier = Modifier
            .let {
                if (idx == 0) {
                    it.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                } else {
                    it.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                }
            }
    ) {
        Image(
            modifier = Modifier
                .width(28.dp)
                .height(28.dp),
            painter = painterResource(id = feature.icon),
            contentDescription = "",
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = feature.title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = feature.dsc,
                color = Grey500,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ReserveButton(
    modifier: Modifier,
    price: Int
) {

    ConstraintLayout(
        modifier = modifier
    ) {
        val (vDivide, cInfo, btnReserve) = createRefs()
        Divider(
            modifier = Modifier.constrainAs(vDivide) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
            color = Grey200,
            thickness = 1.dp,
        )
        Column(
            modifier = Modifier
                .constrainAs(cInfo) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 20.dp, top = 8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$${price}")
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
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = Date().toString(f = "MMM dd yyyy"),
                fontSize = 13.sp
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(btnReserve) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .height(70.dp)
                .width(120.dp)
                .padding(end = 16.dp, bottom = 8.dp, top = 10.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(backgroundColor = RedAirbnb),
            onClick = { }
        ) {
            Text(text = "Reserve", color = Color.White)
        }
    }

}