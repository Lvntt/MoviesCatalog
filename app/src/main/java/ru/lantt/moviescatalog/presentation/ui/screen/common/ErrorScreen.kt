package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@Composable
fun ErrorScreen(
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            ErrorItem()

            Spacer(modifier = Modifier.height(PaddingMedium))

            MoviesCatalogButton(
                containerColor = Accent,
                contentColor = Color.White,
                enabled = true,
                onClick = onRetry,
                text = stringResource(id = R.string.retry)
            )
        }
    }
}