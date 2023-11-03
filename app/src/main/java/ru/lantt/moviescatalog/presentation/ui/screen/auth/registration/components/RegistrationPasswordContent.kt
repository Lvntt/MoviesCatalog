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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthPasswordTextField
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingAccentButton
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.LightAccent
import ru.lantt.moviescatalog.presentation.ui.theme.Padding15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding16
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.Padding8
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20
import ru.lantt.moviescatalog.presentation.uistate.auth.register.RegistrationUiState
import ru.lantt.moviescatalog.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationPasswordContent(
    viewModel: RegistrationViewModel,
    goToAuthorizationScreen: () -> Unit,
    goToMainScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val registrationContent by remember { viewModel.registrationContent }
    val registrationUiState by remember { viewModel.registrationUiState }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(Padding16)
            .composed {
                modifier
            }
    ) {
        when (registrationUiState) {
            RegistrationUiState.Initial -> Unit
            RegistrationUiState.Loading -> Unit
            RegistrationUiState.Error -> LaunchedEffect(Unit) {
                goToAuthorizationScreen()
            }
            RegistrationUiState.Success -> LaunchedEffect(Unit) {
                goToMainScreen()
            }
        }

        Text(
            text = stringResource(id = R.string.registration),
            style = Title_2_B_20,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Padding15))

        AuthPasswordTextField(
            label = stringResource(id = R.string.password),
            textFieldValue = registrationContent.password,
            onValueChange = viewModel::setPassword,
            isError = registrationContent.passwordErrorId != null,
            isVisible = registrationContent.passwordIsVisible,
            onVisibilityClick = viewModel::changePasswordVisibility,
            errorId = registrationContent.passwordErrorId
        )

        Spacer(modifier = Modifier.height(Padding15))

        AuthPasswordTextField(
            label = stringResource(id = R.string.repeat_password),
            textFieldValue = registrationContent.repeatedPassword,
            onValueChange = viewModel::setRepeatedPassword,
            isError = registrationContent.repeatedPasswordErrorId != null,
            isVisible = registrationContent.passwordIsVisible,
            onVisibilityClick = viewModel::changePasswordVisibility,
            errorId = registrationContent.repeatedPasswordErrorId
        )

        Spacer(modifier = Modifier.height(Padding20))

        if (registrationUiState is RegistrationUiState.Loading) {
            LoadingAccentButton(
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            AccentButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.registrationIsAllowed(),
                onClick = viewModel::onRegister,
                text = stringResource(id = R.string.register_1)
            )
        }

        if (registrationContent.isRegistrationError) {
            Spacer(modifier = Modifier.height(Padding8))

            Text(
                text = stringResource(id = R.string.unable_to_register),
                style = Text_R_14,
                color = LightAccent
            )
        }
    }
}