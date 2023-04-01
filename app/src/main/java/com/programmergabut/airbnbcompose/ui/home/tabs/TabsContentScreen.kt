package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.ui.FakePlacesViewModel
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.component.PlacesCard
import com.programmergabut.airbnbcompose.ui.component.PlacesCardShimmer

@Preview
@Composable
fun PreviewTabsContentScreen() {
    TabsContentScreen(
        FakePlacesViewModel(),
        ""
    )
}

@Composable
fun TabsContentScreen(
    viewModel: IPlacesViewModel,
    query: String
) {
    val placesResponse = viewModel.getPlacesPage(query, 10, 10).collectAsLazyPagingItems()

    LaunchedEffect(Unit){
        viewModel.getPlacesPage(
            query = query,
            page = (1..10).random(),
            perPage = 8,
        )
    }

    PagingContent(placesResponse)
}

@Composable
fun PagingContent(
    placesResponse: LazyPagingItems<PlacesCard.PlacesCardData>
) {
    LazyColumn {
        items(
            items = placesResponse,
            key = { it.id }
        ) { task ->
            task?.let {
                PlacesCard(
                    imgUrl = it.imgUrl,
                    contentDescription = it.title,
                    title = it.title,
                    distance = "${it.distance} kilometers away",
                    date = "Oct 29 - Nov 3",
                    price = it.price,
                    rate = it.like.toFloat()
                )
            }
        }

        when (val state = placesResponse.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 28.dp, end = 28.dp, top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            text = "Error",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            text = state.error.message.toString(),
                            fontSize = 12.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        PlacesCardShimmer()
                        PlacesCardShimmer()
                    }
                }
            }
            else -> {}
        }

        when (placesResponse.loadState.append) { // Pagination
            is LoadState.Error -> {}
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }
    }
}