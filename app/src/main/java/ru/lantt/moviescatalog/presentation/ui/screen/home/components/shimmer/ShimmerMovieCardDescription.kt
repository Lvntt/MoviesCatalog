package ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_12
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_13
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall

@Composable
fun ShimmerMovieCardDescription(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PaddingSmall)
        ) {
            ShimmerBox(
                width = 250.dp,
                height = with(LocalDensity.current) { Label_B_16.fontSize.toDp() },
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        ShimmerBox(
            width = 150.dp,
            height = with(LocalDensity.current) { Label_R_12.fontSize.toDp() },
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )

        Spacer(modifier = modifier.height(MovieCardRegularPadding))

        ShimmerBox(
            width = 100.dp,
            height = with(LocalDensity.current) { Label_R_13.fontSize.toDp() },
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )
    }
}