package ru.lantt.moviescatalog.presentation.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.presentation.ui.theme.GenreRoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.theme.Gray750
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_13
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieCardGenres(genres: List<Genre>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(PaddingTiny)
    ) {
        repeat(genres.size) {
            val currentGenre = genres[it]

            Box(
                modifier = Modifier.padding(bottom = PaddingTiny)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Gray750,
                            shape = RoundedCornerShape(GenreRoundedCornerRadius)
                        )
                ) {
                    Box {
                        Text(
                            text = currentGenre.name,
                            style = Label_R_13,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 2.dp, horizontal = PaddingSmall)
                        )
                    }
                }
            }
        }
    }
}