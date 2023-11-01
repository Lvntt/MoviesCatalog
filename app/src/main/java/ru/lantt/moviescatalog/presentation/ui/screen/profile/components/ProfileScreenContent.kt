package ru.lantt.moviescatalog.presentation.ui.screen.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.uistate.profile.ProfileContent
import ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel

@Composable
fun ProfileScreenContent(
    viewModel: ProfileViewModel,
    profile: ProfileContent,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val avatarLink by remember { viewModel.avatarLink }

    Column(
        modifier = Modifier
            .background(Gray900)
            .then(modifier)
            .padding(
                top = PaddingMedium,
                end = PaddingMedium,
                start = PaddingMedium
            )
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ProfileAvatar(
            nickname = profile.nickname,
            avatarLink = avatarLink,
            onLogoutClick = viewModel::logOut
        )

        ProfileInfo(
            viewModel = viewModel
        )

        ProfileButtons(
            isSaveEnabled = viewModel.canSave(),
            onSaveClick = {
                viewModel.editProfile()
                focusManager.clearFocus()
            },
            onCancelClick = {
                viewModel.cancelEditing()
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}