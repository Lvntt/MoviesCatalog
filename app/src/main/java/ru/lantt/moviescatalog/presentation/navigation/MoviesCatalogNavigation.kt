package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            // AuthScreen
        }
        composable(MoviesCatalogDestinations.LOGIN) {
            // LoginScreen
        }
        composable(MoviesCatalogDestinations.REGISTRATION) {
            // RegistrationScreen
        }
    }
}