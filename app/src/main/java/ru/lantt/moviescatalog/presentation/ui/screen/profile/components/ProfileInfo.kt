package ru.lantt.moviescatalog.presentation.ui.screen.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.BirthdayPickerField
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.GenderPicker
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel

@Composable
fun ProfileInfo(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val profileContent by remember { viewModel.profileContent }

    Column(
        modifier = modifier
    ) {
        AuthRegularTextField(
            label = stringResource(id = R.string.email),
            textFieldValue = profileContent.email,
            onValueChange = viewModel::setEmail,
            isError = profileContent.emailErrorId != null,
            errorId = profileContent.emailErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.avatar_link),
            textFieldValue = profileContent.avatarLink,
            onValueChange = viewModel::setAvatarLink,
            isError = profileContent.avatarLinkErrorId != null,
            errorId = profileContent.avatarLinkErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.name),
            textFieldValue = profileContent.name,
            onValueChange = viewModel::setName,
            isError = profileContent.nameErrorId != null,
            errorId = profileContent.nameErrorId
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        GenderPicker(
            selectedItemIndex = profileContent.gender,
            onItemSelection = viewModel::setGender
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        BirthdayPickerField(
            textFieldValue = profileContent.birthDate,
            onDatePick = viewModel::setBirthDate,
            isError = profileContent.birthDateErrorId != null,
            errorId = profileContent.birthDateErrorId
        )
    }

}