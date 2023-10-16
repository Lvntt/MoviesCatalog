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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthBottomBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthPasswordTextField
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthTopBar
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding16
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20
import ru.lantt.moviescatalog.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            AuthTopBar()
        },
        bottomBar = {
            AuthBottomBar(
                descriptionText = stringResource(id = R.string.no_account_yet),
                functionalText = stringResource(id = R.string.register_2),
                onFunctionalTextClick = {}
            )
        }
    ) { paddingValues ->
        // TODO for testing purposes only, to be replaced with VM field
        var passwordIsVisible by remember { mutableStateOf(false)}
        var text by remember { mutableStateOf("")}

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Gray900)
                .padding(Padding16)
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                }
        ) {
            Text(
                text = stringResource(id = R.string.login_3),
                style = Title_2_B_20,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Padding15))

            AuthRegularTextField(
                label = stringResource(id = R.string.login_1),
                textFieldValue = "test",
                onValueChange = {},
                isError = false,
            )

            Spacer(modifier = Modifier.height(Padding15))

            AuthPasswordTextField(
                label = stringResource(id = R.string.password),
                textFieldValue = text,
                onValueChange = {text = it},
                isError = false,
                isVisible = passwordIsVisible,
                onVisibilityClick = {
                    passwordIsVisible = !passwordIsVisible
                }
            )
            
            Spacer(modifier = Modifier.height(Padding20))

            AccentButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.login_2)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}