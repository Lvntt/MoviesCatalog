package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding

@Composable
fun ShimmerMovieAbout(
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MovieRegularPadding)
    ) {
        ShimmerBox(
            width = 60.dp,
            height = 19.dp,
            shimmerStartOffsetX = shimmerStartOffsetX
        )

        repeat(3) {
            ShimmerMovieAboutItem(shimmerStartOffsetX = shimmerStartOffsetX)
        }

    }
}