package com.programmergabut.airbnbcompose.bottomnavigation

import com.programmergabut.airbnbcompose.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Search", R.drawable.ic_search_white,"home")
    object Favorite : BottomNavItem("Favorite", R.drawable.ic_favorite_white,"favorite")
    object Airbnb : BottomNavItem("Airbnb", R.drawable.ic_airbnb,"airbnb")
    object Chat : BottomNavItem("Chat", R.drawable.ic_chat_white,"chat")
    object Profile : BottomNavItem("Profile", R.drawable.ic_profile_white,"profile")
}
