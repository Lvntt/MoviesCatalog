package ru.lantt.moviescatalog.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigationWrapper(
    rootNavController: NavHostController,
    onCloseApp: () -> Unit
) {
    val mainNavController = rememberNavController()

    BackHandler {
        if (mainNavController.previousBackStackEntry == null) onCloseApp()
        else mainNavController.popBackStack()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                buttons = BottomNavItems.items,
                navController = mainNavController,
                onItemClick = {
                    mainNavController.navigate(it.route)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            MainNavigation(
                mainNavController = mainNavController,
                rootNavController = rootNavController
            )
        }
    }
}