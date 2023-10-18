package ru.lantt.moviescatalog.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.BottomNavigationBackground
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_11

object MoviesCatalogDestinations {
    const val AUTHORIZATION = "authorization"
    const val LOGIN = "login"
    const val REGISTRATION = "registration"
    const val MAIN = "main"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"
}

@Composable
fun MoviesCatalogNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MoviesCatalogDestinations.MAIN
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
        composable(MoviesCatalogDestinations.MAIN) {
            // MainScreen
            TestScreen(
                color = Color.Magenta,
                navController = navController
            )
        }
        composable(MoviesCatalogDestinations.FAVORITES) {
            // FavoritesScreen
            TestScreen(
                color = Color.Yellow,
                navController = navController
            )
        }
        composable(MoviesCatalogDestinations.PROFILE) {
            // ProfileScreen
            TestScreen(
                color = Color.Green,
                navController = navController
            )
        }
    }
}

// TODO remove ripple effect
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
                )
            )
        }
    }
}

// TODO remove
@Composable
fun TestScreen(
    color: Color,
    navController: NavController
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
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        )
    }
}