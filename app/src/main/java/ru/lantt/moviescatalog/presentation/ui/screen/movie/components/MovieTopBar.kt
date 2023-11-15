package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultIconSize
import ru.lantt.moviescatalog.presentation.ui.theme.Gray750
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.SmallTopBarHeight
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUiState
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopBar(
    viewModel: MovieViewModel,
    lazyListStateProvider: () -> LazyListState,
    onBack: () -> Unit,
) {
    val movieUiState by remember { viewModel.movieUiState }
    val isTopBarContentShown by remember {
        derivedStateOf {
            movieUiState is MovieUiState.Content
        }
    }

    CenterAlignedTopAppBar(
        modifier = Modifier.height(SmallTopBarHeight),
        title = {
            if (isTopBarContentShown) {
                Text(
                    text =
                    (movieUiState as MovieUiState.Content).movieDetailsContent.name ?: "",
                    style = Title_B_24,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .graphicsLayer {
                            if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                                alpha = 0f
                            } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                                alpha =
                                    lazyListStateProvider().firstVisibleItemScrollOffset * 0.003f
                            }
                        }
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.go_back),
                    modifier = Modifier.size(DefaultIconSize)
                )
            }
        },
        actions = {
            if (isTopBarContentShown) {
                val isInFavorites =
                    (movieUiState as MovieUiState.Content).movieDetailsContent.isInFavorites
                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .graphicsLayer {
                            if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                                alpha = 0f
                            } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                                alpha =
                                    lazyListStateProvider().firstVisibleItemScrollOffset * 0.003f
                            }
                        }
                        .background(color = Gray750, shape = CircleShape)
                        .noRippleClickable {
                            if (isInFavorites) {
                                viewModel.deleteFavoriteMovie()
                            } else {
                                viewModel.addFavoriteMovie()
                            }
                        },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id =
                            if (isInFavorites) R.drawable.heart_filled_icon
                            else R.drawable.heart_outlined_icon
                        ),
                        contentDescription = stringResource(id = R.string.add_to_favorites),
                        modifier = Modifier.padding(PaddingSmall),
                        tint = if (isInFavorites) Accent else Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Gray900,
            scrolledContainerColor = Gray900,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}