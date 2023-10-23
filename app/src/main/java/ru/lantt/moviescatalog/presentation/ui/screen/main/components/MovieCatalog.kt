package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24

@Composable
fun MovieCatalog(
    movies: LazyPagingItems<Movie>
) {
    LazyColumn {
        item {
            FilmCarousel()
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
            count = movies.itemCount,
            key = movies.itemKey { it.id },
            contentType = movies.itemContentType { "Movie" }
        ) {
            movies[it]?.let { movie ->
                MovieCard(
                    movie = movie,
                    modifier = Modifier.padding(horizontal = PaddingMedium)
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

    when (movies.loadState.refresh) {
        // TODO Error screen
        is LoadState.Error -> Unit
        // TODO Loading screen
        is LoadState.Loading -> Unit
        is LoadState.NotLoading -> Unit
    }
}

@Composable
private fun LoadingItem() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(PaddingMedium))
    }
}

@Composable
private fun ErrorItem() {
    Text(
        text = stringResource(id = R.string.an_error_occurred),
        style = Label_B_16
    )
}