package ru.lantt.moviescatalog.presentation.ui.screen.profile.components.shimmer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun ShimmerProfileScreenContent(
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(Gray900)
            .then(modifier)
            .padding(
                top = PaddingMedium,
                end = PaddingMedium,
                start = PaddingMedium
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ShimmerProfileAvatar(shimmerStartOffsetX = shimmerStartOffsetX)

        Spacer(modifier = Modifier.height(5.dp))

        ShimmerProfileInfo(shimmerStartOffsetX = shimmerStartOffsetX)

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun ShimmerProfileScreenContentPreview() {
    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerStartOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )

    ShimmerProfileScreenContent(shimmerStartOffsetX)
}