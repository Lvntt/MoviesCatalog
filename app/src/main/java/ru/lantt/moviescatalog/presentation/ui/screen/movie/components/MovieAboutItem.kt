package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.lantt.moviescatalog.presentation.ui.theme.AboutMovieTitleWidth
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14

@Composable
fun MovieAboutItem(
    titleText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = titleText,
            style = Text_R_14,
            color = Gray400,
            modifier = Modifier.width(AboutMovieTitleWidth)
        )

        Spacer(modifier = Modifier.width(PaddingSmall))

        Text(
            text = text,
            style = Text_R_14,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
    }
}