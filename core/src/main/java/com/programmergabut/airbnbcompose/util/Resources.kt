package com.programmergabut.airbnbcompose.util

sealed class Resources {
    object Loading: Resources()
    object Success: Resources()
    object Error: Resources()
}