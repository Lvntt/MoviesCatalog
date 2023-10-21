package ru.lantt.moviescatalog.presentation.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
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

fun getReviewRatingColor(reviewRating: Int): Color {
    return when {
        reviewRating >= 9 -> Green
        reviewRating >= 8 -> LightGreen
        reviewRating >= 6 -> Yellow
        reviewRating >= 4 -> Orange
        reviewRating >= 3 -> Fire
        else -> Red
    }
}