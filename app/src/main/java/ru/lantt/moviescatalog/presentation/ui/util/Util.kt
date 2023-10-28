package ru.lantt.moviescatalog.presentation.ui.util

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

fun Modifier.shimmerEffect(startOffsetX: Float): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

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
        reviewRating > 8 -> Green
        reviewRating > 6 -> LightGreen
        reviewRating > 4 -> Yellow
        reviewRating > 3 -> Orange
        reviewRating > 2 -> Fire
        else -> Red
    }
}