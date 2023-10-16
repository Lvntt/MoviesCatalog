package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Main

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    text: String
) {
    MoviesCatalogButton(
        modifier = modifier,
        containerColor = Main,
        contentColor = Accent,
        enabled = enabled,
        onClick = onClick,
        text = text
    )
}