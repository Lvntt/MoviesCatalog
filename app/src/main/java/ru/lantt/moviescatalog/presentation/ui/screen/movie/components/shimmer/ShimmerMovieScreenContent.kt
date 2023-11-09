package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun ShimmerMovieScreenContent(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.background(Gray900)
    ) {
        Column {
            ShimmerMovieImage(shimmerStartOffsetXProvider = shimmerStartOffsetXProvider)

            Spacer(modifier = Modifier.height(Padding20))

            ShimmerMovieInfo(
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(Padding20))

            ShimmerMovieDescription(
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(Padding20))

            ShimmerMovieGenres(
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(Padding20))

            ShimmerMovieAbout(
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(Padding20))

            repeat(2) {
                ShimmerMovieReviewItem(
                    shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                    modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }
        }
    }
}

@Preview
@Composable
fun ShimmerMovieScreenContentPreview() {
    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerStartOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )

    ShimmerMovieScreenContent({ shimmerStartOffsetX })

}