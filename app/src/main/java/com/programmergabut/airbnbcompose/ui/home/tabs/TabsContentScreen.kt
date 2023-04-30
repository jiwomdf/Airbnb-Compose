package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.ui.FakePlacesViewModel
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationItem
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationItem.PlaceDetail.dataArg
import com.programmergabut.airbnbcompose.ui.component.PlacesCard
import com.programmergabut.airbnbcompose.ui.component.PlacesCardShimmer
import com.programmergabut.airbnbcompose.ui.theme.Grey500
import com.programmergabut.airbnbcompose.ui.theme.RedAirbnb

@Preview
@Composable
fun PreviewTabsContentScreen() {
    TabsContentScreen(
        FakePlacesViewModel(),
        rememberNavController(),
        "", "", "", ""
    )
}

@Composable
fun TabsContentScreen(
    viewModel: IPlacesViewModel,
    navController: NavController,
    query: String,
    orderBy: String,
    orientation: String,
    color: String,
) {
    val placesResponse = viewModel.getPlacesPage(
        query = query,
        perPage = 10,
        orderBy = orderBy,
        orientation = orientation,
        color = color,
    ).collectAsLazyPagingItems()

    PagingContent(
        placesResponse = placesResponse,
        navController = navController
    )
}

@Composable
fun PagingContent(
    placesResponse: LazyPagingItems<PlacesCardModel.PlacesCardData>,
    navController: NavController,
) {
    LazyColumn {
        items(
            items = placesResponse,
            key = { it.id }
        ) { task ->
            task?.let {
                PlacesCard(
                    modifier = Modifier.fillMaxWidth(),
                    data = it,
                    onClick = {
                        navController
                            .currentBackStackEntry
                            ?.savedStateHandle
                            ?.set(dataArg, it)

                        navController.navigate(NavigationItem.PlaceDetail.screen_route)
                    }
                )
            }
        }

        when (val state = placesResponse.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(start = 28.dp, end = 28.dp, top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = RedAirbnb,
                                        fontSize = 32.sp
                                    )
                                ) {
                                    append("404")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontSize = 32.sp
                                    )
                                ) {
                                    append(" Not Found")
                                }
                            },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            text = (state.error.message ?: "Something went wrong").toString(),
                            fontSize = 12.sp,
                            color = Grey500,
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