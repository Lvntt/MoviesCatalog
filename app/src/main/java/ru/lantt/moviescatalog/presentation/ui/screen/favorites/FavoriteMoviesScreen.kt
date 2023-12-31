package ru.lantt.moviescatalog.presentation.ui.screen.favorites

import android.widget.Toast
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
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.event.FavoritesEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.FavoriteMoviesContent
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.FavoritesTopBar
import ru.lantt.moviescatalog.presentation.ui.screen.favorites.components.shimmer.ShimmerFavoriteMoviesContent
import ru.lantt.moviescatalog.presentation.ui.util.shimmerStartOffsetX
import ru.lantt.moviescatalog.presentation.uistate.favorites.FavoritesUiState
import ru.lantt.moviescatalog.presentation.viewmodel.favorites.FavoritesViewModel

@Composable
fun FavoriteMoviesScreen(
    navController: NavController,
    goToAuthorizationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val favoritesUiState by remember { viewModel.favoritesUiState }
    val shimmerStartOffsetX = shimmerStartOffsetX()

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.favoritesEventFlow.collect { event ->
            when (event) {
                FavoritesEvent.AuthenticationRequired -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.authentication_required),
                        Toast.LENGTH_SHORT
                    ).show()
                    goToAuthorizationScreen()
                }
            }
        }
    }


    Scaffold(
        topBar = {
            FavoritesTopBar()
        }
    ) { paddingValues ->
        when (favoritesUiState) {
            FavoritesUiState.Initial -> Unit
            FavoritesUiState.Loading -> ShimmerFavoriteMoviesContent(
                shimmerStartOffsetXProvider = { shimmerStartOffsetX },
            )
            FavoritesUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
            is FavoritesUiState.Content -> FavoriteMoviesContent(
                favorites = (favoritesUiState as FavoritesUiState.Content).favorites,
                navController = navController,
                shimmerStartOffsetXProvider = { shimmerStartOffsetX },
                modifier = modifier.padding(paddingValues)
            )
        }
    }
}