package com.programmergabut.airbnbcompose.ui.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.ui.PlacesViewModel
import com.programmergabut.airbnbcompose.ui.airbnb.AirbnbScreen
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationItem.PlaceDetail.dataArg
import com.programmergabut.airbnbcompose.ui.inbox.InboxScreen
import com.programmergabut.airbnbcompose.ui.favorite.FavoriteScreen
import com.programmergabut.airbnbcompose.ui.home.HomeScreen
import com.programmergabut.airbnbcompose.ui.placedetail.PlaceDetailScreen
import com.programmergabut.airbnbcompose.ui.profile.ProfileScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
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
            AirbnbScreen()
        }
        composable(BottomNavItem.Chat.screen_route) {
            InboxScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen()
        }
        composable(
            route = NavigationItem.PlaceDetail.screen_route,
        ) {
            val data = navController.previousBackStackEntry?.savedStateHandle?.get<PlacesCardModel.PlacesCardData>(dataArg)
            PlaceDetailScreen(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                data = data
            )
        }
    }
}

val listFullScreenMenu = listOf(NavigationItem.PlaceDetail.screen_route)