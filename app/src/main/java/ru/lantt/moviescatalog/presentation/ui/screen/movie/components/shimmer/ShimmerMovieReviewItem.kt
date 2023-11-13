package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall

@Composable
fun ShimmerMovieReviewItem(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerBox(
                width = 40.dp,
                height = 40.dp,
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
            )

            Spacer(modifier = Modifier.width(MovieRegularPadding))

            ShimmerBox(
                width = 170.dp,
                height = 17.dp,
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
            )
        }

        Spacer(modifier = Modifier.height(PaddingSmall))

        ShimmerBox(
            width = 328.dp,
            height = 71.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )
    }
}