package ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageWidth
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerMovieCardImage(shimmerStartOffsetXProvider: () -> Float) {
    Box(
        modifier = Modifier
            .width(MovieCardImageWidth)
            .height(MovieCardImageHeight)
            .clip(RoundedCornerShape(5.dp))
            .shimmerEffect(shimmerStartOffsetXProvider)
    )
}