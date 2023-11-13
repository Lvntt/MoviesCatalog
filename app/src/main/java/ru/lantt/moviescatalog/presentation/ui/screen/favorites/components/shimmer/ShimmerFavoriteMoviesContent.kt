package ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.shimmer

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.util.shimmerStartOffsetX
import ru.lantt.moviescatalog.presentation.ui.util.shimmerEffect

@Composable
fun ShimmerFavoriteMoviesContent(
    shimmerStartOffsetXProvider: () -> Float,
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
                            height = 270.dp,
                            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        ShimmerBox(
                            width = 170.dp,
                            height = 17.dp,
                            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
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
                            height = 270.dp,
                            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        ShimmerBox(
                            width = 170.dp,
                            height = 17.dp,
                            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Padding20))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect(shimmerStartOffsetXProvider)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(17.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect(shimmerStartOffsetXProvider)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun ShimmerFavoriteMoviesContentPreview() {
    val shimmerStartOffsetX = shimmerStartOffsetX()

    ShimmerFavoriteMoviesContent({ shimmerStartOffsetX })
}