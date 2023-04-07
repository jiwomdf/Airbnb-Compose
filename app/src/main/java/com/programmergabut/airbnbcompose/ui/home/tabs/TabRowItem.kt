package com.programmergabut.airbnbcompose.ui.home.tabs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.ui.IPlacesViewModel
import com.programmergabut.airbnbcompose.ui.PlacesViewModel
import org.koin.androidx.compose.getViewModel

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
            title = "Camping",
            screen = {
                TabsContentScreen(
                    viewModel = viewModel,
                    query = "Camping",
                    navController = navController
                ) },
            icon = R.drawable.ic_home_type_camping,
        ),
        TabRowItem(
            title = "Castle",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Castle",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_historical_homes,
        ),
        TabRowItem(
            title = "Lake side",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Lake side",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_lake,
        ),
        TabRowItem(
            title = "Beach",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Beach",
                navController = navController
            ) },
            icon = R.drawable.ic_home_type_tropical,
        ),
        TabRowItem(
            title = "Chef",
            screen = { TabsContentScreen(
                viewModel = viewModel,
                query = "Chef",
                navController = navController
            ) },
            icon = R.drawable.ic_chef_black,
        )
    )


}