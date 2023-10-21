package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageWidth

@Composable
fun MovieCardImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .width(MovieCardImageWidth)
            .height(MovieCardImageHeight)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}