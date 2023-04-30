package com.programmergabut.airbnbcompose.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.FakePlacesViewModel
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.component.PlacesCardShimmer
import com.programmergabut.airbnbcompose.ui.home.tabs.TabRowItem
import com.programmergabut.airbnbcompose.ui.home.tabs.TabRowItemList
import com.programmergabut.airbnbcompose.ui.home.tabs.TabsContentScreen
import com.programmergabut.airbnbcompose.ui.theme.Grey200
import com.programmergabut.airbnbcompose.util.DebounceStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = FakePlacesViewModel()
    )
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: IPlacesViewModel
) {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState( bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

        val pagerState = rememberPagerState()
        val searchState = remember { mutableStateOf("") }
        var debounceStatus by remember { mutableStateOf(DebounceStatus.FromTab) }

        val orderByState = remember { mutableStateOf("") }
        val colorState = remember { mutableStateOf("") }
        val orientationState = remember { mutableStateOf("") }

        LaunchedEffect(searchState.value) {
            debounceStatus = DebounceStatus.Loading
            delay(1000)
            debounceStatus = if (searchState.value.isBlank()) {
                DebounceStatus.FromTab
            } else {
                DebounceStatus.FromSearch
            }
        }

        LaunchedEffect(
            orderByState.value,
            colorState.value,
            orientationState.value
        ) {
            if(orderByState.value.isNotEmpty() ||
               colorState.value.isNotEmpty() ||
               orientationState.value.isNotEmpty()
            ) {
                debounceStatus = DebounceStatus.Loading
                delay(1000)
                debounceStatus = DebounceStatus.FromSearch
            }
        }

        BottomSheetScaffold(
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    scope.launch {
                        scaffoldState.bottomSheetState.apply {
                            if (isCollapsed) {
                                expand()
                            } else {
                                collapse()
                            }
                        }
                    }
                })
            },
            scaffoldState = scaffoldState,
            sheetContent = {
                SearchSettingScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 300.dp),
                    orderByState = orderByState,
                    colorState = colorState,
                    orientationState = orientationState,
                    onClose = {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
                BackHandler(enabled = true) {
                    scope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            },
            sheetPeekHeight = 0.dp,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        ) {
            SearchBarScreen(
                modifier = Modifier
                    .padding(top = 16.dp),
                shadow = 10.dp,
                searchState = searchState
            ) {
                scope.launch {
                    if(sheetState.isCollapsed){
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            }

            when (debounceStatus) {
                DebounceStatus.FromSearch -> {
                    TabsContentScreen(
                        viewModel = viewModel,
                        navController = navController,
                        query = searchState.value,
                        orderBy = orderByState.value,
                        orientation = orientationState.value,
                        color = colorState.value,
                    )
                }
                DebounceStatus.FromTab -> {
                    val item = TabRowItemList(navController, viewModel)
                    TabBarLayout(
                        modifier = Modifier
                            .padding(top = 14.dp, start = 16.dp, end = 16.dp),
                        pagerState = pagerState,
                        tabs = item.tabRowItems
                    )
                    TabsContent(tabs = item.tabRowItems, pagerState = pagerState)
                    Divider(color = Grey200, thickness = 1.dp)
                }
                DebounceStatus.Loading -> {
                    PlacesCardShimmer()
                    PlacesCardShimmer()
                }
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabBarLayout(
    modifier: Modifier,
    pagerState: PagerState,
    tabs: List<TabRowItem>,
) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Color.Black
            )
        },
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, item ->
            val isSelected = pagerState.currentPage == index
            Tab(
                selected = isSelected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                icon = {
                    Image(
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(color = if (isSelected) Color.Black else Color.Gray)
                    )
                },
                text = {
                    Text(
                        text = item.title,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(color = Color.Black),
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<TabRowItem>,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}