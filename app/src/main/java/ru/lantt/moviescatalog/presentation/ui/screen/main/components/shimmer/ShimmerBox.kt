package ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerBox(
    width: Dp,
    height: Dp,
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(5.dp))
            .shimmerEffect(shimmerStartOffsetX)
    )
}