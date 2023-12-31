package ru.lantt.moviescatalog.presentation.ui.screen.auth.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.SecondaryButton
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding35
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_15
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20

@Composable
fun AuthorizationScreen(
    goToRegistrationScreen: () -> Unit,
    goToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.movie_night_pic),
                contentDescription = null,
                modifier = Modifier.padding(vertical = 56.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.authorization_info_title),
            style = Title_2_B_20,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.authorization_info_text),
            style = Text_R_15,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Padding35))

        AccentButton(
            modifier = modifier.fillMaxWidth(),
            enabled = true,
            onClick = goToRegistrationScreen,
            text = stringResource(id = R.string.registration)
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        SecondaryButton(
            modifier = modifier.fillMaxWidth(),
            enabled = true,
            onClick = goToLoginScreen,
            text = stringResource(id = R.string.login_2)
        )
    }
}