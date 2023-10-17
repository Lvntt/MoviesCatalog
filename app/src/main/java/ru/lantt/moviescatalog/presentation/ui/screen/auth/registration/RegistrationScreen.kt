package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.RegistrationInfoContent
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.RegistrationPasswordContent
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthBottomBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthTopBar
import ru.lantt.moviescatalog.presentation.uistate.RegistrationState
import ru.lantt.moviescatalog.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    onFunctionalTextClick: () -> Unit,
    goToAuthorizationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val registrationState by remember { viewModel.registrationState }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            AuthTopBar(
                onBackButtonClick = {
                    when (registrationState) {
                        is RegistrationState.RegistrationInfo -> goToAuthorizationScreen()
                        is RegistrationState.RegistrationPassword -> viewModel.goToRegistrationInfoScreen()
                    }
                }
            )
        },
        bottomBar = {
            AuthBottomBar(
                descriptionText = stringResource(id = R.string.already_have_an_account),
                functionalText = stringResource(id = R.string.login_4),
                onFunctionalTextClick = onFunctionalTextClick
            )
        }
    ) { paddingValues ->
        when (registrationState) {
            RegistrationState.RegistrationInfo -> RegistrationInfoContent(
                viewModel = viewModel,
                modifier = modifier
                    .padding(paddingValues)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                focusManager.clearFocus()
                            }
                        )
                    }
            )
            RegistrationState.RegistrationPassword -> RegistrationPasswordContent(
                viewModel = viewModel,
                modifier = modifier
                    .padding(paddingValues)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                focusManager.clearFocus()
                            }
                        )
                    }
            )
        }
    }
}