package ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.shimmer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerFavoriteMoviesContent(
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(Gray900)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(65.dp))

        repeat(2) {
            Column(
                modifier = Modifier.padding(
                    start = PaddingMedium,
                    end = PaddingMedium,
                    top = Padding20
                )
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        ShimmerBox(
                            width = 170.dp,
                            height = 320.dp,
                            shimmerStartOffsetX = shimmerStartOffsetX,
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        ShimmerBox(
                            width = 170.dp,
                            height = 17.dp,
                            shimmerStartOffsetX = shimmerStartOffsetX,
                        )
                    }

                    Spacer(modifier = Modifier.width(DefaultPaddingBetweenElements))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        ShimmerBox(
                            width = 170.dp,
                            height = 320.dp,
                            shimmerStartOffsetX = shimmerStartOffsetX,
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        ShimmerBox(
                            width = 170.dp,
                            height = 17.dp,
                            shimmerStartOffsetX = shimmerStartOffsetX,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Padding20))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect(shimmerStartOffsetX)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(17.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect(shimmerStartOffsetX)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun ShimmerFavoriteMoviesContentPreview() {
    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerStartOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )

    ShimmerFavoriteMoviesContent(shimmerStartOffsetX)
}