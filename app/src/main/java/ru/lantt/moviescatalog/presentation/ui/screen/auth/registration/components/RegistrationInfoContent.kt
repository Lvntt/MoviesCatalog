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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20
import ru.lantt.moviescatalog.presentation.viewmodel.auth.RegistrationViewModel

@Composable
fun RegistrationInfoContent(
    viewModel: RegistrationViewModel,
    modifier: Modifier = Modifier
) {
    val registrationContent by remember { viewModel.registrationContent }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(PaddingMedium)
            .then(modifier)
    ) {
        Text(
            text = stringResource(id = R.string.registration),
            style = Title_2_B_20,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.name),
            textFieldValue = registrationContent.name,
            onValueChange = viewModel::setName,
            isError = registrationContent.nameErrorId != null,
            errorId = registrationContent.nameErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        GenderPicker(
            selectedItemIndex = viewModel.getChosenGenderIndex(),
            onItemSelection = viewModel::setGender
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.login_1),
            textFieldValue = registrationContent.login,
            onValueChange = viewModel::setUsername,
            isError = registrationContent.loginErrorId != null,
            errorId = registrationContent.loginErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.email),
            textFieldValue = registrationContent.email,
            onValueChange = viewModel::setEmail,
            isError = registrationContent.emailErrorId != null,
            errorId = registrationContent.emailErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        BirthdayPickerField(
            textFieldValue = registrationContent.dateOfBirth,
            onDatePick = viewModel::setDateOfBirth,
            isError = registrationContent.dateOfBirthErrorId != null,
            errorId = registrationContent.dateOfBirthErrorId
        )

        Spacer(modifier = Modifier.height(Padding20))

        AccentButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = viewModel.canGoToPasswordScreen(),
            onClick = viewModel::goToPasswordScreen,
            text = stringResource(id = R.string.continue_label)
        )
    }
}