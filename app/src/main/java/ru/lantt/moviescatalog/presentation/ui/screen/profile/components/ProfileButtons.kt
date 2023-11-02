package ru.lantt.moviescatalog.presentation.ui.screen.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.SecondaryButton
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements

@Composable
fun ProfileButtons(
    isSaveEnabled: Boolean,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DefaultPaddingBetweenElements)
    ) {
        AccentButton(
            enabled = isSaveEnabled,
            onClick = onSaveClick,
            text = stringResource(id = R.string.save),
            modifier = Modifier.fillMaxWidth()
        )

        SecondaryButton(
            enabled = true,
            onClick = onCancelClick,
            text = stringResource(id = R.string.cancel),
            modifier = Modifier.fillMaxWidth()
        )
    }
}