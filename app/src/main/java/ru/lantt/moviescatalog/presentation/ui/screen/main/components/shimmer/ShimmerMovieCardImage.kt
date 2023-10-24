package ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageWidth
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerMovieCardImage(shimmerStartOffsetX: Float,) {
    Box(
        modifier = Modifier
            .width(MovieCardImageWidth)
            .height(MovieCardImageHeight)
            .shimmerEffect(shimmerStartOffsetX)
    )
}