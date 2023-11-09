package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox

@Composable
fun ShimmerMovieDescription(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    ShimmerBox(
        modifier = modifier,
        width = 330.dp,
        height = 106.dp,
        shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
    )
}