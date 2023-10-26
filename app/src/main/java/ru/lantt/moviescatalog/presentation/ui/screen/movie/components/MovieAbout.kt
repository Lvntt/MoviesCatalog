package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.MovieDetails
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding

@Composable
fun MovieAbout(
    movie: MovieDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MovieRegularPadding)
    ) {
        Text(
            text = stringResource(id = R.string.genres),
            style = Label_B_16,
            color = Color.White
        )

        MovieAboutItem(
            titleText = stringResource(id = R.string.year),
            text = movie.year.toString()
        )

        if (movie.country != null) {
            MovieAboutItem(
                titleText = stringResource(id = R.string.country),
                text = movie.country
            )
        }

        if (movie.tagline != null) {
            MovieAboutItem(
                titleText = stringResource(id = R.string.tagline),
                text = movie.tagline
            )
        }
    }
}