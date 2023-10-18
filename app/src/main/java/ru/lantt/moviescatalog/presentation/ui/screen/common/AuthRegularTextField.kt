package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.presentation.ui.theme.BorderDefault
import ru.lantt.moviescatalog.presentation.ui.theme.LightAccent
import ru.lantt.moviescatalog.presentation.ui.theme.BorderSize
import ru.lantt.moviescatalog.presentation.ui.theme.ErrorTextFieldOpacity
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding12
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.RoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_15

// TODO Remove ugly underline
@Composable
fun AuthRegularTextField(
    label: String,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorId: Int? = null
) {
    Text(
        text = label,
        style = Label_M_15,
        color = White
    )

    Spacer(modifier = Modifier.height(PaddingSmall))

    BasicTextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = Text_R_15.copy(color = White),
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        cursorBrush = SolidColor(White),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (!isError) Gray900 else LightAccent.copy(alpha = ErrorTextFieldOpacity),
                        shape = RoundedCornerShape(RoundedCornerRadius)
                    )
                    .border(
                        width = BorderSize,
                        color = if (!isError) BorderDefault else LightAccent,
                        shape = RoundedCornerShape(RoundedCornerRadius)
                    )
                    .padding(Padding12),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        }
    )

    if (errorId != null) {
        Spacer(modifier = Modifier.height(PaddingSmall))

        Text(
            text = stringResource(id = errorId),
            style = Text_R_14,
            color = LightAccent
        )
    }
}