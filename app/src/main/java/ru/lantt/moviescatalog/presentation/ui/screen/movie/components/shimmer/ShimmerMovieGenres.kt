package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding

@Composable
fun ShimmerMovieGenres(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ShimmerBox(
            width = 60.dp,
            height = 19.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )

        Spacer(modifier = Modifier.height(MovieRegularPadding))

        ShimmerBox(
            width = 328.dp,
            height = 64.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )
    }
}