package com.programmergabut.airbnbcompose

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Search", R.drawable.ic_search_white,"home")
}
