package ru.lantt.moviescatalog.presentation.ui.screen.movie

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.lazy.rememberLazyListState
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
import ru.lantt.moviescatalog.presentation.ui.util.shimmerStartOffsetX
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUiState
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    id: String,
    goToAuthorizationScreen: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    val viewModel: MovieViewModel = koinViewModel(parameters = { parametersOf(id) })
    val movieUiState by remember { viewModel.movieUiState }
    val shimmerStartOffsetX = shimmerStartOffsetX()

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

                MovieEvent.ReviewAdded -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.review_added),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                MovieEvent.ReviewEdited -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.review_edited),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                MovieEvent.ReviewDeleted -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.review_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                MovieEvent.FavoriteMovieAdded -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.added_to_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                MovieEvent.FavoriteMovieDeleted -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.removed_from_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            MovieTopBar(
                viewModel = viewModel,
                onBack = onBack,
                lazyListStateProvider = { lazyListState }
            )
        }
    ) {
        when (movieUiState) {
            MovieUiState.Initial -> Unit
            MovieUiState.Loading -> ShimmerMovieScreenContent(shimmerStartOffsetXProvider = { shimmerStartOffsetX })
            MovieUiState.Error -> ErrorScreen(onRetry = viewModel::refresh)
            is MovieUiState.Content -> MovieScreenContent(
                viewModel = viewModel,
                movie = (movieUiState as MovieUiState.Content).movieDetailsContent,
                shimmerStartOffsetXProvider = { shimmerStartOffsetX },
                lazyListStateProvider = { lazyListState },
                modifier = modifier
            )
        }
    }
}