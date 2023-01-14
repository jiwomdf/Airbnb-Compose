package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.TestViewModel
import com.programmergabut.airbnbcompose.ui.component.PlacesCard
import com.programmergabut.airbnbcompose.ui.component.PlacesCardShimmer
import com.programmergabut.airbnbcompose.ui.component.customShimmer
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun TabCampingScreen(
    viewModel: TestViewModel = getViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val postResponse = viewModel.postResponse.value

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.getFriendList()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
    ) {
        if(postResponse.isLoading){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .customShimmer(),
            ) {
                PlacesCardShimmer()
                PlacesCardShimmer()
            }
        } else {
            for (data in postResponse.data){
                PlacesCard(
                    painter = painterResource(id = R.drawable.place1),
                    contentDescription = data.body,
                    title = data.title,
                    distance = "959 kilometers away",
                    date = "Oct 29 - Nov 3",
                    price = data.id,
                    rate = 4.88F
                )
            }
        }
    }
}
