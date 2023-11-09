package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ru.lantt.moviescatalog.presentation.ui.screen.auth.authorization.AuthorizationScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.login.LoginScreen
import ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.RegistrationScreen
import ru.lantt.moviescatalog.presentation.ui.screen.launch.LaunchScreen
import ru.lantt.moviescatalog.presentation.ui.screen.movie.MovieScreen
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.BottomNavigationBackground
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_11
import ru.lantt.moviescatalog.presentation.ui.util.NoRippleInteractionSource

object MoviesCatalogDestinations {
    const val AUTHORIZATION = "authorization"
    const val LOGIN = "login"
    const val REGISTRATION = "registration"
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"
    const val LAUNCH = "launch"
    const val MOVIE = "movie"
}


@Composable
fun RootNavigation(
    navController: NavHostController,
    onCloseApp: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MoviesCatalogDestinations.LAUNCH
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
                goToHomeScreen = {
                    navController.navigate(MoviesCatalogDestinations.HOME)
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
                goToHomeScreen = {
                    navController.navigate(MoviesCatalogDestinations.HOME)
                }
            )
        }

        composable(MoviesCatalogDestinations.HOME) {
            MainNavigationWrapper(
                rootNavController = navController,
                onCloseApp = onCloseApp
            )
        }

        composable(MoviesCatalogDestinations.LAUNCH) {
            LaunchScreen(
                navController = navController
            )
        }

        composable(
            route = "${MoviesCatalogDestinations.MOVIE}/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieScreen(
                id = movieId,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                goToAuthorizationScreen = {
                    navController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                }
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    buttons: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = BottomNavigationBackground,
        contentColor = Gray400
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.iconId),
                        contentDescription = stringResource(id = item.labelId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelId),
                        style = Label_R_11
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Accent,
                    selectedTextColor = Accent,
                    indicatorColor = BottomNavigationBackground,
                    unselectedIconColor = Gray400,
                    unselectedTextColor = Gray400
                ),
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}