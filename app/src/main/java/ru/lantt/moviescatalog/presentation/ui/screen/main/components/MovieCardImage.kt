package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerMovieCardImage
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardImageWidth
import ru.lantt.moviescatalog.presentation.ui.util.getRatingColor

@Composable
fun MovieCardImage(
    rating: Double?,
    imageUrl: String,
    shimmerStartOffsetX: Float
) {
    var isPosterLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(MovieCardImageWidth)
            .height(MovieCardImageHeight)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                ShimmerMovieCardImage(shimmerStartOffsetX)
            } else {
                isPosterLoaded = true
                SubcomposeAsyncImageContent()
            }
        }
        if (isPosterLoaded && rating != null) {
            val ratingColor = getRatingColor(rating)

            MovieCardRating(
                ratingColor = ratingColor,
                rating = rating
            )
        }
    }
}