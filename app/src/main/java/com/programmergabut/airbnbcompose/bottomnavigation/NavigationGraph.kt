package com.programmergabut.airbnbcompose.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programmergabut.airbnbcompose.AirbnbScreen
import com.programmergabut.airbnbcompose.BottomNavItem
import com.programmergabut.airbnbcompose.ChatScreen
import com.programmergabut.airbnbcompose.FavoriteScreen
import com.programmergabut.airbnbcompose.HomeScreen
import com.programmergabut.airbnbcompose.ProfileScreen

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
