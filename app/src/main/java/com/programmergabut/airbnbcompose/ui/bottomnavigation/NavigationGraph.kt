package com.programmergabut.airbnbcompose.ui.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programmergabut.airbnbcompose.ui.airbnb.AirbnbScreen
import com.programmergabut.airbnbcompose.ui.chat.ChatScreen
import com.programmergabut.airbnbcompose.ui.favorite.FavoriteScreen
import com.programmergabut.airbnbcompose.ui.home.HomeScreen
import com.programmergabut.airbnbcompose.ui.profile.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Favorite.screen_route) {
            FavoriteScreen()
        }
        composable(BottomNavItem.Airbnb.screen_route) {
            AirbnbScreen()
        }
        composable(BottomNavItem.Chat.screen_route) {
            ChatScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen()
        }
    }
}
