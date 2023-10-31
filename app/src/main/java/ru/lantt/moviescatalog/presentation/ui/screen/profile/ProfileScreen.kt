package ru.lantt.moviescatalog.presentation.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.lantt.moviescatalog.domain.entity.Profile
import ru.lantt.moviescatalog.presentation.navigation.BottomNavItems
import ru.lantt.moviescatalog.presentation.navigation.BottomNavigationBar
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.ProfileAvatar
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.ProfileButtons
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.ProfileInfo
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                buttons = BottomNavItems.items,
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .background(Gray900)
                .padding(paddingValues)
                .padding(top = PaddingMedium, end = PaddingMedium, start = PaddingMedium)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ProfileAvatar(
                nickname = profile.nickName,
                avatarLink = profile.avatarLink
            )

            ProfileInfo(
                email = profile.email,
                avatarLink = profile.avatarLink ?: "",
                name = profile.name,
                gender = profile.gender,
                birthDate = profile.birthDate
            )

            ProfileButtons(
                onSaveClick = {},
                onCancelClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}