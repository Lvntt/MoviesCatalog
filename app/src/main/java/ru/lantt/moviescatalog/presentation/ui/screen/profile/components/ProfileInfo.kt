package ru.lantt.moviescatalog.presentation.ui.screen.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Gender
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.BirthdayPickerField
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components.GenderPicker
import ru.lantt.moviescatalog.presentation.ui.screen.common.AuthRegularTextField
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements

@Composable
fun ProfileInfo(
    email: String,
    avatarLink: String,
    name: String,
    gender: Gender,
    birthDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AuthRegularTextField(
            label = stringResource(id = R.string.email),
            textFieldValue = email,
            onValueChange = {},
            isError = false
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.avatar_link),
            textFieldValue = avatarLink,
            onValueChange = {},
            isError = false
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        AuthRegularTextField(
            label = stringResource(id = R.string.name),
            textFieldValue = name,
            onValueChange = {},
            isError = false
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        GenderPicker(
            selectedItemIndex = if (gender == Gender.MALE) 0 else 1,
            onItemSelection = {}
        )

        Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

        BirthdayPickerField(
            onDatePick = {}
        )
    }

}