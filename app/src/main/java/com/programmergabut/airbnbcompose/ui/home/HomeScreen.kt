package com.programmergabut.airbnbcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import com.programmergabut.airbnbcompose.ui.home.tabs.TabRowItem
import com.programmergabut.airbnbcompose.ui.home.tabs.TabRowItemList
import com.programmergabut.airbnbcompose.ui.theme.Grey200
import com.programmergabut.airbnbcompose.util.HideWhenRender
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = FakePlacesViewModel()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: IPlacesViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

        val pagerState = rememberPagerState()

        SearchBar(
            modifier = Modifier
                .padding(top = 16.dp),
            shadow = 10.dp
        )

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
}


@Composable
fun SearchBar(
    modifier: Modifier,
    shadow: Dp = 0.dp
) {
    var searchState by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White,
            elevation = shadow
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .weight(0.2F)
                        .fillMaxHeight()
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    painter = painterResource(id = R.drawable.ic_search_white),
                    contentDescription = "search",
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
                TextField(
                    modifier = Modifier
                        .weight(1.6F)
                        .fillMaxSize()
                        .padding(top = if (searchState.isNotEmpty()) 8.dp else 0.dp),
                    value = searchState,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    onValueChange = {
                        searchState = it
                    },
                    placeholder = {
                        Column {
                            Text(
                                text = "Where to go?",
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                            )
                            Text(
                                text = "Anywhere • Any week • ${(1..10).random()} guest",
                                fontSize = 13.sp
                            )
                        }
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.LightGray, CircleShape),
                        painter = painterResource(id = R.drawable.ic_settings_white),
                        contentDescription = "setting",
                        colorFilter = ColorFilter.tint(color = Color.Black)
                    )
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
                        colorFilter = ColorFilter.tint(color = if(isSelected) Color.Black else Color.Gray)
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