package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.util.formatMoneyNumber
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieDetailsContent

@Composable
fun MovieAbout(
    movie: MovieDetailsContent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MovieRegularPadding)
    ) {
        Text(
            text = stringResource(id = R.string.about_movie),
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

        if (movie.director != null) {
            MovieAboutItem(
                titleText = stringResource(id = R.string.director),
                text = movie.director
            )
        }

        if (movie.budget != null) {
            MovieAboutItem(
                titleText = stringResource(id = R.string.budget),
                text = formatMoneyNumber(movie.budget)
            )
        }

        if (movie.fees != null) {
            MovieAboutItem(
                titleText = stringResource(id = R.string.fees_world),
                text = formatMoneyNumber(movie.fees)
            )
        }

        MovieAboutItem(
            titleText = stringResource(id = R.string.age),
            text = "${movie.ageLimit}+"
        )

        MovieAboutItem(
            titleText = stringResource(id = R.string.time),
            text = "${movie.time} мин."
        )
    }
}