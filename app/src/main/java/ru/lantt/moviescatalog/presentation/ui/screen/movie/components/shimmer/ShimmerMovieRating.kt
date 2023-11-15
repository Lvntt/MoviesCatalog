package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.RatingRoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerMovieRating(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(51.dp)
            .height(26.dp)
            .clip(RoundedCornerShape(RatingRoundedCornerRadius))
            .shimmerEffect(shimmerStartOffsetXProvider)
    )
}