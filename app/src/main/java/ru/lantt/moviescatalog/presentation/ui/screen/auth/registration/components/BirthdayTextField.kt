package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.BorderDefault
import ru.lantt.moviescatalog.presentation.ui.theme.BorderSize
import ru.lantt.moviescatalog.presentation.ui.theme.ErrorTextFieldOpacity
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.IconPadding
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.LightAccent
import ru.lantt.moviescatalog.presentation.ui.theme.Padding12
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.RoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.theme.SmallIconSize
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_15
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun BirthdayTextField(
    textFieldValue: String,
    enabled: Boolean,
    isError: Boolean,
    errorId: Int?,
    onPickDate: () -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.date_of_birth),
            style = Label_M_15,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        BasicTextField(
            value = textFieldValue,
            onValueChange = {},
            textStyle = Text_R_15.copy(color = Color.White),
            enabled = enabled,
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .noRippleClickable {
                                onPickDate()
                            }
                            .background(
                                color = if (!isError) Gray900 else LightAccent.copy(alpha = ErrorTextFieldOpacity),
                                shape = RoundedCornerShape(RoundedCornerRadius)
                            )
                            .border(
                                width = BorderSize,
                                color = if (!isError) BorderDefault else LightAccent,
                                shape = RoundedCornerShape(RoundedCornerRadius)
                            )
                            .padding(
                                start = Padding12,
                                top = Padding12,
                                bottom = Padding12,
                                end = IconPadding
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        innerTextField()
                    }
                    IconButton(
                        onClick = onPickDate
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
                            contentDescription = stringResource(id = R.string.pick_date_of_birth),
                            tint = Gray400,
                            modifier = Modifier.size(SmallIconSize)
                        )
                    }
                }
            }
        )

        if (errorId != null) {
            Spacer(modifier = Modifier.height(PaddingSmall))

            Text(
                text = stringResource(id = errorId),
                color = LightAccent,
                style = Text_R_14
            )
        }
    }
}