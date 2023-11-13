package ru.lantt.moviescatalog.presentation.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import retrofit2.HttpException
import ru.lantt.moviescatalog.presentation.common.ErrorCodes.BAD_REQUEST
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogDestinations
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.MovieCatalog
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerMovieCatalog
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.util.shimmerStartOffsetX
import ru.lantt.moviescatalog.presentation.viewmodel.main.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val updatedMovies = remember { viewModel.updatedMovies }

    val shimmerStartOffsetX = shimmerStartOffsetX()

    when (movies.loadState.refresh) {
        is LoadState.Error -> {
            val exception = (movies.loadState.refresh as LoadState.Error).error
            if (exception is HttpException) {
                when (exception.code()) {
                    BAD_REQUEST -> {
                        LaunchedEffect(Unit) {
                            viewModel.logout()
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
        is LoadState.Loading -> ShimmerMovieCatalog(shimmerStartOffsetXProvider = { shimmerStartOffsetX })
        is LoadState.NotLoading -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray900)
            ) {
                MovieCatalog(
                    movies = movies,
                    updatedMovies = updatedMovies,
                    goToMovieScreen = {
                        navController.navigate("${MoviesCatalogDestinations.MOVIE}/${it}")
                    },
                    shimmerStartOffsetXProvider = { shimmerStartOffsetX }
                )
            }
        }
    }
}