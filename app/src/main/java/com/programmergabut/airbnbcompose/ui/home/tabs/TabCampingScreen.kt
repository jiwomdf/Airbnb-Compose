package com.programmergabut.airbnbcompose.ui.home.tabs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.programmergabut.airbnbcompose.ui.TestViewModel
import com.programmergabut.airbnbcompose.ui.component.PlacesCard
import com.programmergabut.airbnbcompose.ui.component.PlacesCardShimmer
import com.programmergabut.airbnbcompose.ui.component.customShimmer
import com.programmergabut.airbnbcompose.util.Resources
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun TabCampingScreen(
    viewModel: TestViewModel = getViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val postResponse = viewModel.placesCardResponse.value

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.getCollections(
                    query = "Lake Side",
                    page = 1,
                    perPage = 10,
                )
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
        when(postResponse.state) {
            is Resources.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .customShimmer(),
                ) {
                    PlacesCardShimmer()
                    PlacesCardShimmer()
                }
            }
            is Resources.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(610.dp)
                ) {
                    items(postResponse.data){ data ->
                        PlacesCard(
                            imgUrl = data.imgUrl,
                            contentDescription = data.title,
                            title = data.title,
                            distance = "${data.distance} kilometers away",
                            date = "Oct 29 - Nov 3",
                            price = data.price,
                            rate = data.like.toFloat()
                        )
                    }
                }
            }
            is Resources.Error -> {
                Toast.makeText(LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
