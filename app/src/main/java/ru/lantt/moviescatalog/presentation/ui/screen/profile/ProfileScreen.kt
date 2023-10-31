package ru.lantt.moviescatalog.presentation.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.presentation.navigation.BottomNavItems
import ru.lantt.moviescatalog.presentation.navigation.BottomNavigationBar
import ru.lantt.moviescatalog.presentation.ui.event.ProfileEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingScreen
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.ProfileScreenContent
import ru.lantt.moviescatalog.presentation.uistate.profile.ProfileUiState
import ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    goToAuthorizationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val profileUiState by remember { viewModel.profileUiState }
    val profileContent by remember { viewModel.profileContent }

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.profileEventFlow.collect { event ->
            when (event) {
                ProfileEvent.AuthenticationRequired -> goToAuthorizationScreen()
            }
        }
    }


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
        when (profileUiState) {
            ProfileUiState.Initial -> Unit
            ProfileUiState.Loading -> LoadingScreen()
            ProfileUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
            is ProfileUiState.Success -> ProfileScreenContent(
                viewModel = viewModel,
                profile = profileContent,
                modifier = modifier.padding(paddingValues)
            )
        }
    }
}