package ru.lantt.moviescatalog.presentation.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import retrofit2.HttpException
import ru.lantt.moviescatalog.presentation.common.ErrorCodes.BAD_REQUEST
import ru.lantt.moviescatalog.presentation.navigation.BottomNavItems
import ru.lantt.moviescatalog.presentation.navigation.BottomNavigationBar
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogDestinations
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.MovieCatalog
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerMovieCatalog
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.viewmodel.main.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

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
        when (movies.loadState.refresh) {
            is LoadState.Error -> {
                val exception = (movies.loadState.refresh as LoadState.Error).error.cause
                if (exception is HttpException) {
                    when (exception.code()) {
                        BAD_REQUEST -> {
                            LaunchedEffect(Unit) {
                                navController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                            }
                        }
                    }
                }
                ErrorScreen(
                    onRetry = {
                        movies.retry()
                    }
                )
            }
            is LoadState.Loading -> ShimmerMovieCatalog()
            is LoadState.NotLoading -> {
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