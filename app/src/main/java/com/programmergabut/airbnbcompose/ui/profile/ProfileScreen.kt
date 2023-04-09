package com.programmergabut.airbnbcompose.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.domain.model.SettingModel
import com.programmergabut.airbnbcompose.ui.FakePlacesViewModel
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.component.Divide
import com.programmergabut.airbnbcompose.ui.theme.Grey500
import com.programmergabut.airbnbcompose.util.generateSetting

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        viewModel = FakePlacesViewModel()
    )
}

@Composable
fun ProfileScreen(
    viewModel: IPlacesViewModel
) {
    val settings = viewModel.getSettings()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Profile",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 40.sp,
        )
        Profile(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 8.dp)
        )
        YourPlace(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 8.dp)
        )
        Divide()
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Settings",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
        )
        SettingList(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            settings = settings
        )
    }
}

@Composable
fun Profile(
    modifier: Modifier
) {
    Box(modifier = modifier
        .clickable { }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (iIcon, tTitle, iArrow, divider) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(iIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(start = 16.dp)
                    .width(60.dp)
                    .height(60.dp),
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .constrainAs(tTitle) {
                        start.linkTo(iIcon.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Person name",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
                Text(
                    text = "Show Profile",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Grey500
                )
            }
            Image(
                modifier = Modifier
                    .constrainAs(iArrow) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(end = 16.dp)
                    .width(25.dp)
                    .height(25.dp),
                painter = painterResource(id = R.drawable.ic_arrow_forward_ios),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.DarkGray)
            )
            Divider(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .constrainAs(divider) {
                        top.linkTo(tTitle.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                color = Color.LightGray,
                thickness = 1.dp,
            )
        }
    }
}

@Composable
fun YourPlace(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (tTitle, tSubTitle, ivIcon) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(tTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(start = 16.dp, top = 16.dp),
                text = "Airbnb your place",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .constrainAs(tSubTitle) {
                        top.linkTo(tTitle.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = 16.dp, end = 4.dp),
                text = "it's simple to get set up and \nstart earning",
                color = Grey500
            )
            Image(
                modifier = Modifier
                    .constrainAs(ivIcon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.ic_img),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun SettingList(
    modifier: Modifier,
    settings: List<SettingModel>
) {
    Column {
        for (i in settings.indices) {
            val setting = settings[i]
            ConstraintLayout(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
            ) {
                val (iIcon, tTitle, iArrow, divider) = createRefs()
                Image(
                    modifier = Modifier
                        .constrainAs(iIcon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(start = 16.dp)
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = setting.icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
                Text(
                    modifier = Modifier
                        .constrainAs(tTitle) {
                            start.linkTo(iIcon.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    text = setting.title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
                Image(
                    modifier = Modifier
                        .constrainAs(iArrow) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(end = 16.dp)
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_forward_ios),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.DarkGray)
                )
                if(i != settings.size - 1){
                    Divider(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .constrainAs(divider) {
                                top.linkTo(tTitle.bottom)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            },
                        color = Color.LightGray,
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}
