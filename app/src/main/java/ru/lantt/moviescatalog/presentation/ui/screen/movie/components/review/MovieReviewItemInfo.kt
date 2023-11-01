package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.MovieCardReviewRating
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding

@Composable
fun MovieReviewItemInfo(
    nickname: String?,
    rating: Int?,
    isAnonymous: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MovieRegularPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            if (isAnonymous) {
                Text(
                    text = stringResource(id = R.string.anonymous_user),
                    style = Label_M_14,
                    color = Color.White
                )
            } else {
                if (nickname != null) {
                    Text(
                        text = nickname,
                        style = Label_M_14,
                        color = Color.White
                    )
                }
            }
        }

        MovieCardReviewRating(reviewRating = rating)
    }
}