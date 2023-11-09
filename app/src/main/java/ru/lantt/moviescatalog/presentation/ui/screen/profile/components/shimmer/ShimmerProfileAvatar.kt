package ru.lantt.moviescatalog.presentation.ui.screen.profile.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900

@Composable
fun ShimmerProfileAvatar(
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray900),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShimmerBox(
            width = 88.dp,
            height = 88.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
            modifier = Modifier.clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(6.dp))

        ShimmerBox(
            width = 160.dp,
            height = 29.dp,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider
        )

        Box {
            ShimmerBox(
                width = 140.dp,
                height = 18.dp,
                shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}