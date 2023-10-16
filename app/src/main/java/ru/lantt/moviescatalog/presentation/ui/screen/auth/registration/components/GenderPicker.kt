package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.SegmentedButton
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding8

@Composable
fun GenderPicker(
    onItemSelection: (Int) -> Unit
) {
    Text(
        text = stringResource(id = R.string.gender),
        style = Label_M_15,
        color = Color.White
    )

    Spacer(modifier = Modifier.height(Padding8))

    SegmentedButton(
        labels = listOf(
            stringResource(id = R.string.man),
            stringResource(id = R.string.woman)
        ),
        onItemSelection = onItemSelection
    )
}