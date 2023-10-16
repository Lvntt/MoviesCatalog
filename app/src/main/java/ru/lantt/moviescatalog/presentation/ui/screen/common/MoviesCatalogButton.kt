package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.lantt.moviescatalog.presentation.ui.theme.DisabledButtonOpacity
import ru.lantt.moviescatalog.presentation.ui.theme.Label_SB_15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding12
import ru.lantt.moviescatalog.presentation.ui.theme.RoundedCornerRadius

@Composable
fun MoviesCatalogButton(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(size = RoundedCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = DisabledButtonOpacity),
            disabledContentColor = contentColor.copy(alpha = DisabledButtonOpacity)
        ),
        contentPadding = PaddingValues(Padding12)
    ) {
        Text(
            text = text,
            style = Label_SB_15
        )
    }
}