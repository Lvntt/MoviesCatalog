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
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24

@Composable
fun MovieCatalog(
    movies: List<Movie>
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

        items(movies.size) {
            MovieCard(
                movie = movies[it],
                modifier = Modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
        }
    }
}