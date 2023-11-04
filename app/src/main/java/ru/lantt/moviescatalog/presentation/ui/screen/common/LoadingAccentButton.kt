package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.DisabledButtonOpacity
import ru.lantt.moviescatalog.presentation.ui.theme.Padding12
import ru.lantt.moviescatalog.presentation.ui.theme.RoundedCornerRadius

@Composable
fun LoadingAccentButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {},
        enabled = false,
        shape = RoundedCornerShape(size = RoundedCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            contentColor = Color.White,
            disabledContainerColor = Accent,
            disabledContentColor = Color.White.copy(alpha = DisabledButtonOpacity)
        ),
        contentPadding = PaddingValues(Padding12)
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun LoadingAccentButtonPreview() {
    Column {
        LoadingAccentButton(
            modifier = Modifier.fillMaxWidth()
        )
    }
}