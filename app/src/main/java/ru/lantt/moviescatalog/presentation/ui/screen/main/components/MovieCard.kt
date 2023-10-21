package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.lantt.moviescatalog.data.datasource.MockMovieSource
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardRegularPadding
import ru.lantt.moviescatalog.presentation.ui.util.getReviewRatingColor

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    val reviewRatingColor = getReviewRatingColor(movie.reviewRating)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray900)
    ) {
        MovieCardImage(imageUrl = movie.poster)

        Spacer(modifier = Modifier.width(MovieCardRegularPadding))

        MovieCardDescription(
            name = movie.name,
            reviewRatingColor = reviewRatingColor,
            reviewRating = movie.reviewRating.toString(),
            year = movie.year.toString(),
            country = movie.country,
            genres = movie.genres
        )
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(movie = MockMovieSource.movies[0])
}