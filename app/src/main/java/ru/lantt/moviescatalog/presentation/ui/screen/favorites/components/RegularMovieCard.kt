package ru.lantt.moviescatalog.presentation.ui.screen.favorites.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.screen.home.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun RegularMovieCard(
    movie: Movie,
    shimmerStartOffsetXProvider: () -> Float,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .noRippleClickable {
                onMovieClick(movie.id)
            }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(movie.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    ShimmerBox(
                        width = 170.dp,
                        height = 320.dp,
                        shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                    )
                } else {
                    SubcomposeAsyncImageContent()
                }
            }

            Box(
                modifier = Modifier.matchParentSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                MovieCardRating(
                    reviewRating = movie.reviewRating,
                    modifier = Modifier.padding(top = 2.dp, end = 1.5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = movie.name,
            style = Label_M_14,
            color = Color.White
        )
    }
}