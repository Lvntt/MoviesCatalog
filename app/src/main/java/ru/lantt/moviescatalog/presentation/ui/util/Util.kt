package ru.lantt.moviescatalog.presentation.ui.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Fire
import ru.lantt.moviescatalog.presentation.ui.theme.Green
import ru.lantt.moviescatalog.presentation.ui.theme.LightGreen
import ru.lantt.moviescatalog.presentation.ui.theme.Orange
import ru.lantt.moviescatalog.presentation.ui.theme.Red
import ru.lantt.moviescatalog.presentation.ui.theme.Yellow

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    // TODO вынести transition и startOffsetX наверх, передавать в каждый modifier
    // TODO startOffsetX в параметры функции
    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF161616),
                Accent,
                Color(0xFF161616)
            ),
            start = Offset(startOffsetX * size.width.toFloat(), 0f),
            end = Offset(startOffsetX * size.width.toFloat() + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

fun getRatingColor(reviewRating: Double): Color {
    return when {
        reviewRating >= 9 -> Green
        reviewRating >= 8 -> LightGreen
        reviewRating >= 6 -> Yellow
        reviewRating >= 4 -> Orange
        reviewRating >= 3 -> Fire
        else -> Red
    }
}