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
        screen = { TabScreen(text = "Tab 1") },
        icon = R.drawable.ic_home_type_camping,
    ),
    TabRowItem(
        title = "Historical",
        screen = { TabScreen(text = "Tab 2") },
        icon = R.drawable.ic_home_type_historical_homes,
    ),
    TabRowItem(
        title = "Like side",
        screen = { TabScreen(text = "Tab 3") },
        icon = R.drawable.ic_home_type_lake,
    ),
    TabRowItem(
        title = "Tropical",
        screen = { TabScreen(text = "Tab 4") },
        icon = R.drawable.ic_home_type_tropical,
    )
)
