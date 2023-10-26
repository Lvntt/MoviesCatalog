package ru.lantt.moviescatalog.presentation.ui.screen.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.lantt.moviescatalog.data.datasource.MockDetailedMovieSource
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieDescription
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieGenres
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieImage
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieInfo
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieTopBar
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun MovieScreen(
    modifier: Modifier = Modifier
) {
    val movie = MockDetailedMovieSource.movie

    Scaffold(
        topBar = {
            MovieTopBar(onBackButtonClick = {})
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .background(Gray900)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(Padding20)
        ) {
            item {
                MovieImage(posterLink = movie.poster)
            }

            item {
                MovieInfo(
                    name = movie.name,
                    rating = movie.rating
                )
            }

            item {
                MovieDescription(
                    description = movie.description
                )
            }

            item {
                MovieGenres(
                    genres = movie.genres,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )
            }

            item {

            }
        }
    }

}


@Preview
@Composable
fun MovieScreenPreview() {
    MovieScreen()
}