package com.programmergabut.airbnbcompose.ui.home

import androidx.compose.runtime.Composable
import com.programmergabut.airbnbcompose.R

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

val tabRowItems = listOf(
    TabRowItem(
        title = "Camping",
        screen = { TabCampingScreen(text = "tab_camping") },
        icon = R.drawable.ic_home_type_camping,
    ),
    TabRowItem(
        title = "Historical",
        screen = { TabHistoricalScreen(text = "tab_historical") },
        icon = R.drawable.ic_home_type_historical_homes,
    ),
    TabRowItem(
        title = "Lake side",
        screen = { TabLakeSideScreen(text = "tab_like_side") },
        icon = R.drawable.ic_home_type_lake,
    ),
    TabRowItem(
        title = "Tropical",
        screen = { TabTropicalScreen(text = "tab_tropical") },
        icon = R.drawable.ic_home_type_tropical,
    )
)
