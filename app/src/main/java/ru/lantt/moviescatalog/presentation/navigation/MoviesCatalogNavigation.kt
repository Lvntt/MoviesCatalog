package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lantt.moviescatalog.presentation.ui.screen.auth.authorization.AuthorizationScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.login.LoginScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.RegistrationScreen
import ru.lantt.moviescatalog.presentation.ui.screen.main.MainScreen

object MoviesCatalogDestinations {
    const val AUTHORIZATION = "authorization"
    const val LOGIN = "login"
    const val REGISTRATION = "registration"
    const val MAIN = "main"
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
            AuthorizationScreen(
                goToRegistrationScreen = {
                    navController.navigate(MoviesCatalogDestinations.REGISTRATION)
                },
                goToLoginScreen = {
                    navController.navigate(MoviesCatalogDestinations.LOGIN)
                }
            )
        }
        composable(MoviesCatalogDestinations.LOGIN) {
            LoginScreen(
                onFunctionalTextClick = {
                    navController.navigate(MoviesCatalogDestinations.REGISTRATION)
                },
                goToAuthorizationScreen = {
                    navController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                },
                goToMainScreen = {
                    navController.navigate(MoviesCatalogDestinations.MAIN)
                }
            )
        }
        composable(MoviesCatalogDestinations.REGISTRATION) {
            RegistrationScreen(
                onFunctionalTextClick = {
                    navController.navigate(MoviesCatalogDestinations.LOGIN)
                },
                goToAuthorizationScreen = {
                    navController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                },
                goToMainScreen = {
                    navController.navigate(MoviesCatalogDestinations.MAIN)
                }
            )
        }
        composable(MoviesCatalogDestinations.MAIN) {
            MainScreen()
        }
    }
}