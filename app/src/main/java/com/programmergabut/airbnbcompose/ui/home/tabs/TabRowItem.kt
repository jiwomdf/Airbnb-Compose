package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.runtime.Composable
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.PlacesViewModel
import org.koin.androidx.compose.getViewModel

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

val tabRowItems = listOf(
    TabRowItem(
        title = "Camping",
        screen = {
        TabsContentScreen(
            viewModel = getViewModel<PlacesViewModel>(),
            query = "Camping"
        ) },
        icon = R.drawable.ic_home_type_camping,
    ),
    TabRowItem(
        title = "Historical",
        screen = { TabsContentScreen(
            viewModel = getViewModel<PlacesViewModel>(),
            query = "Historical"
        ) },
        icon = R.drawable.ic_home_type_historical_homes,
    ),
    TabRowItem(
        title = "Lake side",
        screen = { TabsContentScreen(
            viewModel = getViewModel<PlacesViewModel>(),
            query = "Lake side"
        ) },
        icon = R.drawable.ic_home_type_lake,
    ),
    TabRowItem(
        title = "Tropical",
        screen = { TabsContentScreen(
            viewModel = getViewModel<PlacesViewModel>(),
            query = "Tropical"
        ) },
        icon = R.drawable.ic_home_type_tropical,
    ),
    TabRowItem(
        title = "Chef",
        screen = { TabsContentScreen(
            viewModel = getViewModel<PlacesViewModel>(),
            query = "Chef"
        ) },
        icon = R.drawable.ic_chef_black,
    )
)
