package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun ShimmerMovieAboutItem(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ShimmerBox(
            width = 80.dp,
            height = 17.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )

        Spacer(modifier = Modifier.width(PaddingMedium))

        ShimmerBox(
            width = 230.dp,
            height = 17.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )
    }
}