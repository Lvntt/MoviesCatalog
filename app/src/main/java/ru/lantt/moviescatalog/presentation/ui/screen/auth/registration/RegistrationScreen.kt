package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.RegistrationPasswordContent
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthBottomBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthTopBar

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        },
        bottomBar = {
            AuthBottomBar(
                descriptionText = stringResource(id = R.string.already_have_an_account),
                functionalText = stringResource(id = R.string.login_4),
                onFunctionalTextClick = {}
            )
        }
    ) { paddingValues ->
//        RegistrationInfoContent(modifier = modifier.padding(paddingValues))
        RegistrationPasswordContent(modifier = modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}