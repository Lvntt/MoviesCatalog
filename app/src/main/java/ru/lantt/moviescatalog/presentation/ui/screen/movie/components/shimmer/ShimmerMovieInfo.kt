package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox

@Composable
fun ShimmerMovieInfo(
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerMovieRating(shimmerStartOffsetX)

        ShimmerBox(
            width = 212.dp,
            height = 26.dp,
            shimmerStartOffsetX = shimmerStartOffsetX
        )

        ShimmerBox(
            width = 40.dp,
            height = 40.dp,
            shimmerStartOffsetX = shimmerStartOffsetX
        )
    }
}