package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorItem
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingItem
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24

private const val CAROUSEL_SIZE = 4

@Composable
fun MovieCatalog(
    movies: LazyPagingItems<Movie>,
    shimmerStartOffsetX: Float
) {
    if (movies.itemCount < CAROUSEL_SIZE) return
    val carouselMovies = mutableListOf<Movie>()
    repeat(CAROUSEL_SIZE) { index ->
        movies[index]?.let { carouselMovies.add(it) }
    }

    LazyColumn {
        item {
            FilmCarousel(movies = carouselMovies)
        }

        item {
            Text(
                text = stringResource(id = R.string.catalog),
                style = Title_B_24,
                color = Color.White,
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

        items(
            count = movies.itemCount - CAROUSEL_SIZE,
            key = movies.itemKey { it.id },
            contentType = movies.itemContentType { "Movie" }
        ) {
            movies[it + CAROUSEL_SIZE]?.let { movie ->
                MovieCard(
                    movie = movie,
                    shimmerStartOffsetX = shimmerStartOffsetX,
                    modifier = Modifier.padding(horizontal = PaddingMedium),
                )
            }

            Spacer(modifier = Modifier.height(PaddingMedium))
        }

        when (movies.loadState.append) {
            is LoadState.Error -> item { ErrorItem() }
            is LoadState.Loading -> item { LoadingItem() }
            is LoadState.NotLoading -> Unit
        }
    }
}