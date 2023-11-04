package ru.lantt.moviescatalog.presentation.ui.screen.favorites

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.presentation.navigation.BottomNavItems
import ru.lantt.moviescatalog.presentation.navigation.BottomNavigationBar
import ru.lantt.moviescatalog.presentation.ui.event.FavoritesEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.FavoriteMoviesContent
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.FavoritesTopBar
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.shimmer.ShimmerFavoriteMoviesContent
import ru.lantt.moviescatalog.presentation.uistate.favorites.FavoritesUiState
import ru.lantt.moviescatalog.presentation.viewmodel.favorites.FavoritesViewModel

@Composable
fun FavoriteMoviesScreen(
    navController: NavController,
    goToAuthorizationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favoritesUiState by remember { viewModel.favoritesUiState }

    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerStartOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )


    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.favoritesEventFlow.collect { event ->
            when (event) {
                FavoritesEvent.AuthenticationRequired -> goToAuthorizationScreen()
            }
        }
    }


    Scaffold(
        topBar = {
            FavoritesTopBar()
        },
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
        when (favoritesUiState) {
            FavoritesUiState.Initial -> Unit
            FavoritesUiState.Loading -> ShimmerFavoriteMoviesContent(
                shimmerStartOffsetX = shimmerStartOffsetX,
            )
            FavoritesUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
            is FavoritesUiState.Content -> FavoriteMoviesContent(
                favorites = (favoritesUiState as FavoritesUiState.Content).favorites,
                navController = navController,
                shimmerStartOffsetX = shimmerStartOffsetX,
                modifier = modifier.padding(paddingValues)
            )
        }
    }
}