package com.programmergabut.airbnbcompose.ui.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.programmergabut.airbnbcompose.ui.theme.Grey500

@Preview
@Composable
fun FavoriteScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (tWish, tTitle, tDsc) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(tWish) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(16.dp),
            text = "Wishlists",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 40.sp,
        )

        Text(
            modifier = Modifier
                .constrainAs(tTitle) {
                    top.linkTo(tWish.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp, top = 32.dp),
            text = "Create your first wishlist",
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 28.sp,
        )
        Text(
            modifier = Modifier
                .constrainAs(tDsc) {
                    top.linkTo(tTitle.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp, top = 8.dp),
            text = "As you search, tap the heart icon to save your favorite places to stay or things to do to a wishlist",
            color = Grey500,
            fontSize = 16.sp,
        )
    }
}