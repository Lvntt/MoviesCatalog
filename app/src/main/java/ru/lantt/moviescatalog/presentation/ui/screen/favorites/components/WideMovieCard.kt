package ru.lantt.moviescatalog.presentation.ui.screen.favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14

@Composable
fun WideMovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(movie.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            if (movie.reviewRating != null) {
                Box(
                    modifier = Modifier.matchParentSize(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    MovieCardRating(
                        reviewRating = movie.reviewRating,
                        modifier = Modifier.padding(top = 2.dp, end = 2.dp)
                    )
                }
            }
        }
        Text(
            text = movie.name,
            style = Label_M_14,
            color = Color.White
        )
    }
}