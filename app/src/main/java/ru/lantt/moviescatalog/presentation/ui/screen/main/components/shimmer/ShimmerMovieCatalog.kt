package ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24

@Composable
fun ShimmerMovieCatalog(shimmerStartOffsetX: Float) {
    LazyColumn {
        item {
            ShimmerFilmCarousel(shimmerStartOffsetX = shimmerStartOffsetX)
        }

        item {
            ShimmerText(
                width = 100.dp,
                height = with(LocalDensity.current) { Title_B_24.fontSize.toDp() },
                shimmerStartOffsetX = shimmerStartOffsetX,
                modifier = Modifier.padding(
                    top = PaddingMedium,
                    start = PaddingMedium,
                    end = PaddingMedium
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))
        }

        items(count = 5) {
            ShimmerMovieCard(
                shimmerStartOffsetX = shimmerStartOffsetX,
                modifier = Modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
        }
    }
}
