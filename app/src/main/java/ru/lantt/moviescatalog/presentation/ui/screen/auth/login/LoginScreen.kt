package ru.lantt.moviescatalog.presentation.ui.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthBottomBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthPasswordTextField
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthTopBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingAccentButton
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.LightAccent
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20
import ru.lantt.moviescatalog.presentation.uistate.auth.login.LoginUiState
import ru.lantt.moviescatalog.presentation.viewmodel.auth.LoginViewModel

@Composable
fun LoginScreen(
    onFunctionalTextClick: () -> Unit,
    goToAuthorizationScreen: () -> Unit,
    goToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val loginContent by remember { viewModel.loginContent }
    val loginUiState by remember { viewModel.loginUiState }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            AuthTopBar(
                onBackButtonClick = goToAuthorizationScreen
            )
        },
        bottomBar = {
            AuthBottomBar(
                descriptionText = stringResource(id = R.string.no_account_yet),
                functionalText = stringResource(id = R.string.register_2),
                onFunctionalTextClick = onFunctionalTextClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Gray900)
                .padding(PaddingMedium)
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                }
        ) {
            when (loginUiState) {
                LoginUiState.Initial -> Unit
                LoginUiState.Loading -> Unit
                LoginUiState.Error -> LaunchedEffect(Unit) {
                    goToAuthorizationScreen()
                }
                LoginUiState.Success -> LaunchedEffect(Unit) {
                    goToHomeScreen()
                }
            }

            Text(
                text = stringResource(id = R.string.login_3),
                style = Title_2_B_20,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

            AuthRegularTextField(
                label = stringResource(id = R.string.login_1),
                textFieldValue = loginContent.login,
                onValueChange = viewModel::setLogin,
                isError = loginContent.isError
            )

            Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

            AuthPasswordTextField(
                label = stringResource(id = R.string.password),
                textFieldValue = loginContent.password,
                onValueChange = viewModel::setPassword,
                isError = loginContent.isError,
                isVisible = loginContent.passwordIsVisible,
                onVisibilityClick = viewModel::changePasswordVisibility
            )

            if (loginContent.isError) {
                Spacer(modifier = Modifier.height(PaddingSmall))

                Text(
                    text = stringResource(id = R.string.invalid_login_or_password_error),
                    style = Text_R_14,
                    color = LightAccent
                )
            }

            Spacer(modifier = Modifier.height(Padding20))

            if (loginUiState is LoginUiState.Loading) {
                LoadingAccentButton(
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                AccentButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel.canLogIn(),
                    onClick = viewModel::logIn,
                    text = stringResource(id = R.string.login_2)
                )
            }
        }
    }
}