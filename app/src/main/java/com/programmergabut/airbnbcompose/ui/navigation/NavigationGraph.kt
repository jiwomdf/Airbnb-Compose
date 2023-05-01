package com.programmergabut.airbnbcompose.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.ui.PlacesViewModel
import com.programmergabut.airbnbcompose.ui.menus.airbnb.AirbnbScreen
import com.programmergabut.airbnbcompose.ui.bottomnavigation.BottomNavItem
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationItem
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationItem.PlaceDetail.dataArg
import com.programmergabut.airbnbcompose.ui.menus.favorite.FavoriteScreen
import com.programmergabut.airbnbcompose.ui.menus.home.HomeScreen
import com.programmergabut.airbnbcompose.ui.menus.inbox.InboxScreen
import com.programmergabut.airbnbcompose.ui.menus.placedetail.PlaceDetailScreen
import com.programmergabut.airbnbcompose.ui.menus.profile.ProfileScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            val viewModel = getViewModel<PlacesViewModel>()
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(BottomNavItem.Favorite.screen_route) {
            FavoriteScreen()
        }
        composable(BottomNavItem.Airbnb.screen_route) {
            AirbnbScreen(
                navController = navController,
            )
        }
        composable(BottomNavItem.Chat.screen_route) {
            InboxScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            val viewModel = getViewModel<PlacesViewModel>()
            ProfileScreen(
                viewModel = viewModel
            )
        }
        composable(
            route = NavigationItem.PlaceDetail.screen_route,
        ) {
            val viewModel = getViewModel<PlacesViewModel>()
            val data =
                navController.previousBackStackEntry?.savedStateHandle?.get<PlacesCardModel.PlacesCardData>(
                    dataArg
                )
            PlaceDetailScreen(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                data = data,
                viewModel = viewModel,
                scaffoldState = scaffoldState
            )
        }
    }
}

val listFullScreenMenu = listOf(NavigationItem.PlaceDetail.screen_route)