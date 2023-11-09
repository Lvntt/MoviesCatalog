package ru.lantt.moviescatalog.presentation.ui.screen.profile.components.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall

@Composable
fun ShimmerProfileInfo(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        repeat(5) {
            Column {
                ShimmerBox(
                    width = 144.dp,
                    height = 18.dp,
                    shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
                )

                Spacer(modifier = Modifier.height(PaddingSmall))

                ShimmerBox(
                    width = 144.dp,
                    height = 42.dp,
                    shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))
        }
    }

}