package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun MovieDescription(
    description: String?,
    modifier: Modifier = Modifier
) {
    if (description != null) {
        MovieExpandableDescription(
            text = description,
            color = Color.White,
            modifier = modifier.padding(horizontal = PaddingMedium)
        )
    }
}