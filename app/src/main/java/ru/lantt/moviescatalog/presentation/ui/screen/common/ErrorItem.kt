package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16

@Composable
fun ErrorItem() {
    Text(
        text = stringResource(id = R.string.an_error_occurred),
        style = Label_B_16
    )
}