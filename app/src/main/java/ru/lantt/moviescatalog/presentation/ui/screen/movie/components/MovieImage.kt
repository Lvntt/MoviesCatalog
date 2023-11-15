package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import ru.lantt.moviescatalog.presentation.ui.theme.CarouselImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900

@Composable
fun MovieImage(
    posterLink: String?,
    firstVisibleItemScrollOffsetProvider: () -> Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(CarouselImageHeight)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(posterLink)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 1f - firstVisibleItemScrollOffsetProvider() * 0.0007f
                    translationY = firstVisibleItemScrollOffsetProvider() * 0.6f
                    scaleX = 1 + firstVisibleItemScrollOffsetProvider() * 0.0005f
                    scaleY = 1 + firstVisibleItemScrollOffsetProvider() * 0.0005f
                }
        ) {
            SubcomposeAsyncImageContent()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Gray900,
                        ),
                        startY = 0f,
                        endY = 1800f
                    )
                )
        )
    }
}