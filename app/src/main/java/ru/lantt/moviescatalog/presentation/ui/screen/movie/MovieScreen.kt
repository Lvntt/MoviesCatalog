package ru.lantt.moviescatalog.presentation.ui.screen.movie

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.lantt.moviescatalog.presentation.ui.event.MovieEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.common.LoadingScreen
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieScreenContent
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieTopBar
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUiState
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    id: String,
    goToAuthorizationScreen: () -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: MovieViewModel = koinViewModel(parameters = { parametersOf(id) })
    val movieUiState by remember { viewModel.movieUiState }

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.movieEventFlow.collect { event ->
            when (event) {
                MovieEvent.AuthenticationRequired -> goToAuthorizationScreen()
            }
        }
    }

    Scaffold(
        topBar = {
            MovieTopBar(onBackButtonClick = onBackButtonClick)
        }
    ) {
        when (movieUiState) {
            MovieUiState.Initial -> Unit
            MovieUiState.Loading -> LoadingScreen()
            MovieUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
            is MovieUiState.Content -> MovieScreenContent(
                viewModel = viewModel,
                movie = (movieUiState as MovieUiState.Content).movieDetailsContent,
                modifier = modifier
            )
        }
    }
}