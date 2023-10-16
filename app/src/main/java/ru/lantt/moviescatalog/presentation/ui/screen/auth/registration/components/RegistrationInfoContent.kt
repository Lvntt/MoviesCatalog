package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding16
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20

@Composable
fun RegistrationInfoContent(
    modifier: Modifier = Modifier
) {
    // TODO for testing purposes only, to be replaced with VM field
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(Padding16)
            .composed {
                modifier
            }
    ) {
        Text(
            text = stringResource(id = R.string.registration),
            style = Title_2_B_20,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Padding15))

        AuthRegularTextField(
            label = stringResource(id = R.string.name),
            textFieldValue = "test",
            onValueChange = {},
            isError = false,
            enabled = true
        )

        Spacer(modifier = Modifier.height(Padding15))

        GenderPicker(onItemSelection = {})

        Spacer(modifier = Modifier.height(Padding15))

        AuthRegularTextField(
            label = stringResource(id = R.string.login_1),
            textFieldValue = "test",
            onValueChange = {},
            isError = false,
            enabled = true
        )

        Spacer(modifier = Modifier.height(Padding15))

        AuthRegularTextField(
            label = stringResource(id = R.string.email),
            textFieldValue = "test",
            onValueChange = {},
            isError = false,
            enabled = true
        )

        Spacer(modifier = Modifier.height(Padding15))

        BirthdayPickerField(
            textFieldValue = "21.11.2004",
            onDatePick = {
                // TODO invoke VM method
            }
        )

        Spacer(modifier = Modifier.height(Padding20))

        AccentButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.continue_label)
        )
    }
}