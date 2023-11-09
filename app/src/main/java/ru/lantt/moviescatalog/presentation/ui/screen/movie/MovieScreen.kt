package ru.lantt.moviescatalog.presentation.ui.screen.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.event.MovieEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieScreenContent
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieTopBar
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.shimmer.ShimmerMovieScreenContent
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
    val context = LocalContext.current
    val viewModel: MovieViewModel = koinViewModel(parameters = { parametersOf(id) })
    val movieUiState by remember { viewModel.movieUiState }
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
        viewModel.movieEventFlow.collect { event ->
            when (event) {
                MovieEvent.AuthenticationRequired -> {
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
            MovieTopBar(onBackButtonClick = onBackButtonClick)
        }
    ) {
        when (movieUiState) {
            MovieUiState.Initial -> Unit
            MovieUiState.Loading -> ShimmerMovieScreenContent(shimmerStartOffsetXProvider = { shimmerStartOffsetX })
            MovieUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
            is MovieUiState.Content -> MovieScreenContent(
                viewModel = viewModel,
                movie = (movieUiState as MovieUiState.Content).movieDetailsContent,
                shimmerStartOffsetXProvider = { shimmerStartOffsetX },
                modifier = modifier
            )
        }
    }
}