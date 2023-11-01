package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.GenreRoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieGenres(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.genres),
            style = Label_B_16,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(MovieRegularPadding))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            repeat(genres.size) {
                val currentGenre = genres[it]

                Box(
                    modifier = Modifier.padding(bottom = PaddingSmall)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Accent,
                                shape = RoundedCornerShape(GenreRoundedCornerRadius)
                            )
                    ) {
                        Box {
                            Text(
                                text = currentGenre.name,
                                style = Label_M_15,
                                color = Color.White,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}