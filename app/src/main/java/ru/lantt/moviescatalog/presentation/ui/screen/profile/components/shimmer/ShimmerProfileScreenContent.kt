package ru.lantt.moviescatalog.presentation.ui.screen.profile.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.util.shimmerStartOffsetX

@Composable
fun ShimmerProfileScreenContent(
    shimmerStartOffsetXProvider: () -> Float,
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
        ShimmerProfileAvatar(shimmerStartOffsetXProvider = shimmerStartOffsetXProvider)

        Spacer(modifier = Modifier.height(5.dp))

        ShimmerProfileInfo(shimmerStartOffsetXProvider = shimmerStartOffsetXProvider)

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun ShimmerProfileScreenContentPreview() {
    val shimmerStartOffsetX = shimmerStartOffsetX()

    ShimmerProfileScreenContent({ shimmerStartOffsetX })
}