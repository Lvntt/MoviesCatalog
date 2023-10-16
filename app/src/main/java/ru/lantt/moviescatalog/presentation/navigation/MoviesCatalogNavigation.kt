package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lantt.moviescatalog.presentation.ui.screen.auth.authorization.AuthorizationScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.login.LoginScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.RegistrationScreen

object MoviesCatalogDestinations {
    const val AUTHORIZATION = "authorization"
    const val LOGIN = "login"
    const val REGISTRATION = "registration"
}

@Composable
fun MoviesCatalogNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MoviesCatalogDestinations.AUTHORIZATION
    ) {
        composable(MoviesCatalogDestinations.AUTHORIZATION) {
            AuthorizationScreen(navController = navController)
        }
        composable(MoviesCatalogDestinations.LOGIN) {
            LoginScreen()
        }
        composable(MoviesCatalogDestinations.REGISTRATION) {
            RegistrationScreen()
        }
    }
}