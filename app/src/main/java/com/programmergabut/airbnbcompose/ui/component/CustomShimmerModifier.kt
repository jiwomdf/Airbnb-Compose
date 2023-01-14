package com.programmergabut.airbnbcompose.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

fun Modifier.customShimmer(): Modifier = composed {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = ShimmerTheme(
            animationSpec = infiniteRepeatable(
                animation = tween(
                    800,
                    easing = LinearEasing,
                    delayMillis = 500,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            blendMode = defaultShimmerTheme.blendMode,
            rotation = defaultShimmerTheme.rotation,
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 0.25f),
                Color.Unspecified.copy(alpha = 0.50f),
                Color.Unspecified.copy(alpha = 0.25f),
            ),
            shaderColorStops = defaultShimmerTheme.shaderColorStops,
            shimmerWidth = defaultShimmerTheme.shimmerWidth,
        )
    )
    shimmer(customShimmer = shimmer)
}
