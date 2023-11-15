package ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardRegularPadding

@Composable
fun ShimmerMovieCard(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        ShimmerMovieCardImage(shimmerStartOffsetXProvider = shimmerStartOffsetXProvider)

        Spacer(modifier = Modifier.width(MovieCardRegularPadding))

        ShimmerMovieCardDescription(shimmerStartOffsetXProvider = shimmerStartOffsetXProvider)
    }
}