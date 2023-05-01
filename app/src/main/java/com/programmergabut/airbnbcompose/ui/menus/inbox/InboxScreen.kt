package com.programmergabut.airbnbcompose.ui.menus.inbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.programmergabut.airbnbcompose.ui.menus.inbox.tabs.TabRowInboxItem
import com.programmergabut.airbnbcompose.ui.menus.inbox.tabs.TabRowInboxItemList
import kotlinx.coroutines.launch

@Preview
@Composable
fun InboxScreenPreview() {
    InboxScreen()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InboxScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp)
    ) {
        val pagerState = rememberPagerState()

        Text(
            text = "Inbox",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 40.sp,
        )

        val item = TabRowInboxItemList()
        TabBarInboxLayout(
            modifier = Modifier
                .padding(top = 32.dp),
            pagerState = pagerState,
            tabs = item.tabRowItems
        )
        TabsInboxContent(tabs = item.tabRowItems, pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabBarInboxLayout(
    modifier: Modifier,
    pagerState: PagerState,
    tabs: List<TabRowInboxItem>,
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
fun TabsInboxContent(
    tabs: List<TabRowInboxItem>,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}