package com.programmergabut.airbnbcompose.ui.menus.home.tabs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

class TabRowItemList(
    navController: NavController,
    viewModel: IPlacesViewModel,
) {

    val tabRowItems = listOf(
        TabRowItem(
            title = "Castle",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Castle",
                orderBy = "",
                orientation = "",
                color = "",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_historical_homes,
        ),
        TabRowItem(
            title = "Camping",
            screen = {
                TabsContentScreen(
                    viewModel = viewModel,
                    query = "Camping",
                    orderBy = "",
                    orientation = "",
                    color = "",
                    navController = navController
                ) },
            icon = R.drawable.ic_home_type_camping,
        ),
        TabRowItem(
            title = "Cabin",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Cabin",
                orderBy = "",
                orientation = "",
                color = "",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_cabin,
        ),
        TabRowItem(
            title = "Beach",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Beach",
                orderBy = "",
                orientation = "",
                color = "",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_tropical,
        ),
        TabRowItem(
            title = "Food",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Food",
                orderBy = "",
                orientation = "",
                color = "",
                navController = navController
            ) },
            icon = R.drawable.ic_chef_black,
        )
    )


}