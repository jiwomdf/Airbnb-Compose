package com.programmergabut.airbnbcompose.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun HideWhenRender(renderScreen: @Composable () -> Unit) {
    if (!LocalInspectionMode.current) {
        renderScreen.invoke()
    }
}