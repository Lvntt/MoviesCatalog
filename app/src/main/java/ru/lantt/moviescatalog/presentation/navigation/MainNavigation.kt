package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.FavoriteMoviesScreen
import ru.lantt.moviescatalog.presentation.ui.screen.main.HomeScreen
import ru.lantt.moviescatalog.presentation.ui.screen.profile.ProfileScreen

@Composable
fun MainNavigation(
    mainNavController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = MoviesCatalogDestinations.HOME
    ) {
        composable(MoviesCatalogDestinations.HOME) {
            HomeScreen(
                navController = rootNavController
            )
        }

        composable(MoviesCatalogDestinations.FAVORITES) {
            FavoriteMoviesScreen(
                navController = rootNavController,
                goToAuthorizationScreen = {
                    rootNavController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                }
            )
        }

        composable(MoviesCatalogDestinations.PROFILE) {
            ProfileScreen(
                goToAuthorizationScreen = {
                    rootNavController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                }
            )
        }
    }
}