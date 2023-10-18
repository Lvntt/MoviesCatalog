package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.lantt.moviescatalog.presentation.ui.theme.Accent

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    text: String
) {
    MoviesCatalogButton(
        modifier = modifier,
        containerColor = Accent,
        contentColor = Color.White,
        enabled = enabled,
        onClick = onClick,
        text = text
    )
}