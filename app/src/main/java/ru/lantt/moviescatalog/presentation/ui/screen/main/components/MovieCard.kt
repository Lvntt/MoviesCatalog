package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardRegularPadding
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUpdateModel

@Composable
fun MovieCard(
    movie: Movie,
    movieUpdateModel: MovieUpdateModel?,
    shimmerStartOffsetXProvider: () -> Float,
    goToMovieScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray900)
            .noRippleClickable {
                goToMovieScreen(movie.id)
            }
    ) {
        MovieCardImage(
            rating = if (movieUpdateModel != null) movieUpdateModel.movieRating else movie.rating,
            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
            imageUrl = movie.poster
        )

        Spacer(modifier = Modifier.width(MovieCardRegularPadding))

        MovieCardDescription(
            name = movie.name,
            reviewRating = if (movieUpdateModel != null) movieUpdateModel.userRating else movie.reviewRating,
            year = movie.year.toString(),
            country = movie.country,
            genres = movie.genres
        )
    }
}