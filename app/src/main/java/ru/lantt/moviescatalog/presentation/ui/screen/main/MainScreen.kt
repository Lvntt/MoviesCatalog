package ru.lantt.moviescatalog.presentation.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.presentation.navigation.BottomNavItems
import ru.lantt.moviescatalog.presentation.navigation.BottomNavigationBar
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingScreen
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.MovieCatalog
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.uistate.main.MainUiState
import ru.lantt.moviescatalog.presentation.viewmodel.main.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val mainUiState by remember { viewModel.mainUiState }

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
        when (mainUiState) {
            MainUiState.Initial -> Unit
            MainUiState.Error -> ErrorScreen(
                onRetry = {
                    viewModel.retry()
                    movies.retry()
                }
            )
            MainUiState.Loading -> LoadingScreen()
            MainUiState.Success -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Gray900)
                        .padding(paddingValues)
                ) {
                    MovieCatalog(movies = movies)
                }
            }
        }
    }
}