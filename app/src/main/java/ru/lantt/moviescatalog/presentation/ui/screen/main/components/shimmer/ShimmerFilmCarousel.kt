package ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.presentation.ui.theme.CarouselImageHeight
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerFilmCarousel(
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(CarouselImageHeight)
            .shimmerEffect(shimmerStartOffsetX)
    )
}