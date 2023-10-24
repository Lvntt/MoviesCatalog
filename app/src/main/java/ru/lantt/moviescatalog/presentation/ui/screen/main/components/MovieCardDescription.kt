package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_12
import ru.lantt.moviescatalog.presentation.ui.theme.MovieCardRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny

@Composable
fun MovieCardDescription(
    name: String,
    reviewRating: Int?,
    year: String,
    country: String,
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = Label_B_16,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(MovieCardRegularPadding))

            MovieCardReviewRating(
                reviewRating = reviewRating
            )
        }

        Spacer(modifier = Modifier.height(PaddingTiny))

        Text(
            text = "$year · $country",
            style = Label_R_12,
            color = Color.White
        )

        Spacer(modifier = modifier.height(MovieCardRegularPadding))

        MovieCardGenres(genres = genres)
    }
}